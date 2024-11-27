package fukuoka.soongsil_carries_love.domain.highschool.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fukuoka.soongsil_carries_love.domain.highschool.dto.*;
import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.highschool.repository.HighschoolRepository;
import fukuoka.soongsil_carries_love.domain.highschool.converter.HighschoolConverter;
import fukuoka.soongsil_carries_love.domain.user.repository.UserRepository;
import fukuoka.soongsil_carries_love.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HighschoolServiceImpl implements HighschoolService {
    private final HighschoolRepository highschoolRepository;
    private final HighschoolConverter highschoolConverter;
    private final UserRepository userRepository;
    private final WebClient webClient = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl("https://open.neis.go.kr")
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                    .followRedirect(false))) // redirection 비활성화
            .build();

    private static final String API_KEY = "1af243782bd7497aa14d6d696af38d25";
    private static final int PAGE_SIZE = 100;

    @Override
    public void fetchAndSaveHighschoolData() {
        int currentPage = 1; // 시작 페이지
        boolean hasNextPage = true;

        // 기존 DB에 저장된 schoolCode를 Set으로 가져와 중복 체크
        Set<String> existingSchoolCodes = new HashSet<>(highschoolRepository.findAllSchoolCodes());

        while (hasNextPage) {
            int finalCurrentPage = currentPage;

            String response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/hub/schoolInfo")
                            .queryParam("KEY", API_KEY)
                            .queryParam("Type", "json")
                            .queryParam("pIndex", finalCurrentPage)
                            .queryParam("pSize", PAGE_SIZE)
                            .queryParam("SCHUL_KND_SC_NM", "고등학교")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // 동기 처리

            if (response == null || response.isEmpty()) {
                break;
            }

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response);
                JsonNode rows = rootNode.path("schoolInfo").path(1).path("row");

                if (rows.isArray()) {
                    for (JsonNode row : rows) {
                        String schoolCode = row.path("SD_SCHUL_CODE").asText().trim();
                        String schoolName = row.path("SCHUL_NM").asText().trim();
                        String coedu = row.path("COEDU_SC_NM").asText().trim();
                        String sido = row.path("LCTN_SC_NM").asText().trim();

                        // 중복 확인: 기존 Set에 schoolCode가 없을 경우에만 저장
                        if (!schoolCode.isEmpty() && !existingSchoolCodes.contains(schoolCode)) {
                            HighschoolFetchResponseDto highschoolDto = new HighschoolFetchResponseDto();
                            highschoolDto.setSchoolCode(schoolCode);
                            highschoolDto.setSchoolName(schoolName);
                            highschoolDto.setCoeduType(coedu);
                            highschoolDto.setSido(sido);

                            Highschool highschoolEntity = highschoolConverter.toEntity(highschoolDto);
                            highschoolRepository.save(highschoolEntity);

                            // 저장 후 Set에 schoolCode 추가
                            existingSchoolCodes.add(schoolCode);
                        }
                    }
                }

                // 페이지 크기가 100보다 작으면 마지막 페이지로 간주
                hasNextPage = rows.size() == PAGE_SIZE;
            } catch (JsonProcessingException e) {
                System.err.println("JSON 처리 중 오류 발생: " + e.getMessage());
                break;
            }

            currentPage++; // 다음 페이지로 이동
        }
    }

    @Override
    public List<String> getAllSchoolNames() {

        List<Highschool> all = highschoolRepository.findAll();
        Collections.sort(all, Comparator.comparing(Highschool::getSchoolName));

        return all.stream().map(highschool -> highschool.getSchoolCode() + "," + highschool.getSchoolName() + "(" + highschool.getSido() + ")")
                .collect(Collectors.toList());
    }

    @Override
    public HighschoolUserCountResponseDto getUserCountByHighschool(String highschoolCode) {
        long userCount = userRepository.countByHighschool_SchoolCode(highschoolCode);

        // 유저가 없는 경우 예외 처리
        if (userCount == 0) {
            throw new IllegalArgumentException(highschoolCode + "에 속해있는 유저가 존재하지 않습니다.");
        }

        return highschoolConverter.toUserCountResponse(highschoolCode, userCount);
    }

    @Override
    public HighschoolGenderRatioResponseDto getGenderRatioByHighschool(String highschoolCode) {
        long total = userRepository.countByHighschool_SchoolCode(highschoolCode);
        long maleCount = userRepository.countByHighschool_SchoolCodeAndGender(highschoolCode, Gender.MALE);
        long femaleCount = userRepository.countByHighschool_SchoolCodeAndGender(highschoolCode, Gender.FEMALE);
        long noneCount = userRepository.countByHighschool_SchoolCodeAndGender(highschoolCode, Gender.NONE);

        double maleRatio = total == 0 ? 0 : (double) maleCount / total * 100;
        double femaleRatio = total == 0 ? 0 : (double) femaleCount / total * 100;
        double noneRatio = total == 0 ? 0 : (double) noneCount / total * 100;

        return highschoolConverter.toGenderRatioResponse(highschoolCode, maleRatio, femaleRatio, noneRatio);
    }

    @Override
    public HighschoolUserStudentIdResponseDto getStudentCountByYear(String highschoolCode) {

        if (!highschoolRepository.existsByHighschoolCode(highschoolCode)) {
            throw new IllegalArgumentException("해당 고등학교 코드를 가진 사용자가 없습니다: " + highschoolCode);
        }

        List<String> studentIds = highschoolRepository.findStudentIdsByHighschoolCode(highschoolCode);
        Map<String, Long> studentIdDistribution = studentIds.stream()
                .filter(Objects::nonNull) // Null 값 제거
                .map(studentId -> {
                    try {
                        // studentId의 xx 부분 추출
                        return studentId.substring(2, 4);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid studentId format: " + studentId);
                    }
                })
                .collect(Collectors.groupingBy(year -> year, Collectors.counting()));

        return highschoolConverter.toResponseDto(studentIdDistribution);
    }

    @Override
    public List<HighschoolRankingofUserCountResponseDto> getUserCountByHighschool() {
        List<Object[]> result = highschoolRepository.findUserCountByHighschool();

        return result.stream()
                .map(record -> new HighschoolRankingofUserCountResponseDto((String) record[0], (Long) record[1]))
                .collect(Collectors.toList());
    }
}
