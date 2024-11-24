package fukuoka.soongsil_carries_love.domain.highschool.converter;

import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolFetchResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.stereotype.Component;

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
                .build();
    }
}
