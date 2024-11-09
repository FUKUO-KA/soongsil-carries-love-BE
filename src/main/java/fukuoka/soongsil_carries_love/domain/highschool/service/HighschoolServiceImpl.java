package fukuoka.soongsil_carries_love.domain.highschool.service;

import fukuoka.soongsil_carries_love.domain.highschool.converter.HighschoolConverter;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolRequestDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.highschool.repository.HighschoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HighschoolServiceImpl implements HighschoolService {

    private final HighschoolRepository highschoolRepository;
    private final HighschoolConverter highschoolConverter;
    private final WebClient webClient;

    @Override
    public void fetchAndSaveHighschoolData() {
        // Open API 호출하여 모든 데이터를 받아오기 (필터링을 위해 hsScNm 포함)
        List<HighschoolRequestDto> requestDtos = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/hub/schoolInfo")
                        .queryParam("Type", "json")
                        .queryParam("pSize", 2800)
                        .queryParam("KEY", "1af243782bd7497aa14d6d696af38d25")
                        .build())
                .retrieve()
                .bodyToFlux(HighschoolRequestDto.class) // hsScNm을 포함하는 DTO
                .collectList()
                .block();

        if (requestDtos == null || requestDtos.isEmpty()) {
            System.out.println("No data fetched from API.");
            return;
        }

        System.out.println("Total records fetched from API: " + requestDtos.size());
        System.out.println("Raw API data: " + requestDtos);

        // hsScNm 필드를 이용해 필터링한 후 필요한 필드만 남기기
        List<HighschoolResponseDto> filteredHighschools = requestDtos.stream()
                .filter(dto -> dto.getHS_SC_NM() != null && List.of("일반고", "자율고", "특목고", "특성화고").contains(dto.getHS_SC_NM()))
                .map(dto -> new HighschoolResponseDto(dto.getSCHUL_NM(), dto.getSD_SCHUL_CODE())) // 필요한 필드만 매핑
                .collect(Collectors.toList());

        System.out.println("Filtered records (only 일반고, 자율고, 특목고, 특성화고): " + filteredHighschools.size());
        filteredHighschools.forEach(dto ->
                System.out.println("Highschool Name: " + dto.getHighSchoolName() + ", School Code: " + dto.getSchoolCode())
        );

        // 기존 데이터베이스의 schoolCode를 가져와서 중복 검사
        List<String> existingSchoolCodes = highschoolRepository.findAll().stream()
                .map(Highschool::getSchoolCode)
                .collect(Collectors.toList());

        System.out.println("Existing school codes in database: " + existingSchoolCodes.size());

        // 새로운 데이터 중 기존 데이터에 없는 것만 추가
        List<Highschool> newHighschools = filteredHighschools.stream()
                .filter(dto -> !existingSchoolCodes.contains(dto.getSchoolCode()))
                .map(highschoolConverter::convertToEntity)
                .collect(Collectors.toList());

        System.out.println("New highschools to be added to the database: " + newHighschools.size());

        // DB에 일괄 삽입
        highschoolRepository.saveAll(newHighschools);

        // 기존 데이터 중 API에 없는 데이터 삭제
        existingSchoolCodes.stream()
                .filter(code -> filteredHighschools.stream().noneMatch(dto -> dto.getSchoolCode().equals(code)))
                .forEach(code -> {
                    System.out.println("Deleting highschool with school code: " + code);
                    highschoolRepository.deleteBySchoolCode(code);
                });

        System.out.println("Data synchronization completed.");
    }

    @Override
    public List<HighschoolResponseDto> getAllHighschools() {
        return highschoolConverter.convertToResponseDtoList(highschoolRepository.findAll());
    }
}
