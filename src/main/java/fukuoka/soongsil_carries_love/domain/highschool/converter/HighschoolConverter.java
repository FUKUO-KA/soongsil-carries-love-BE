package fukuoka.soongsil_carries_love.domain.highschool.converter;

import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HighschoolConverter {

    public Highschool convertToEntity(HighschoolResponseDto dto) {
        return Highschool.builder()
                .highSchoolName(dto.getHighSchoolName())
                .schoolCode(dto.getSchoolCode())
                .build();
    }

    public HighschoolResponseDto convertToResponseDto(Highschool highschool) {
        HighschoolResponseDto responseDto = new HighschoolResponseDto();
        responseDto.setHighSchoolName(highschool.getHighSchoolName());
        responseDto.setSchoolCode(highschool.getSchoolCode());
        return responseDto;
    }

    public List<HighschoolResponseDto> convertToResponseDtoList(List<Highschool> highschools) {
        return highschools.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
