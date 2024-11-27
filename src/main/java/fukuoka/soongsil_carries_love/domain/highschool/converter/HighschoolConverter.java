package fukuoka.soongsil_carries_love.domain.highschool.converter;

import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolFetchResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolGenderRatioResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolUserCountResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolUserStudentIdResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HighschoolConverter {

    /*
     HighschoolResponseDto -> Highschool 엔티티
     */
    public Highschool toEntity(HighschoolFetchResponseDto highschoolFetchResponseDto) {
        return Highschool.builder()
                .schoolCode(highschoolFetchResponseDto.getSchoolCode())
                .schoolName(highschoolFetchResponseDto.getSchoolName())
                .coeduType(highschoolFetchResponseDto.getCoeduType())
                .sido(highschoolFetchResponseDto.getSido())
                .build();
    }

    public HighschoolUserCountResponseDto toUserCountResponse(String highschoolCode, long userCount) {
        HighschoolUserCountResponseDto response = new HighschoolUserCountResponseDto();
        response.setHighschoolCode(highschoolCode);
        response.setUserCount(userCount);
        return response;
    }

    public HighschoolGenderRatioResponseDto toGenderRatioResponse(String highschoolCode, double maleRatio, double femaleRatio, double noneRatio) {
        HighschoolGenderRatioResponseDto response = new HighschoolGenderRatioResponseDto();
        response.setHighschoolCode(highschoolCode);
        response.setMaleRatio(maleRatio);
        response.setFemaleRatio(femaleRatio);
        response.setNoneRatio(noneRatio);
        return response;
    }

    public HighschoolUserStudentIdResponseDto toResponseDto(Map<String, Long> studentIdDistribution) {
        return new HighschoolUserStudentIdResponseDto(studentIdDistribution);
    }
}
