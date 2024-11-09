package fukuoka.soongsil_carries_love.domain.highschool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HighschoolRequestDto {
    private String SCHUL_NM;  // 고등학교 이름
    private String SD_SCHUL_CODE;      // 학교 코드
    private String HS_SC_NM;          // 고등학교 구분 (필터링 용도, ex: 일반고, 특목고, 자율고, 특성화고)

    @Override
    public String toString() {
        return "HighschoolRequestDto{" +
                "highSchoolName='" + SCHUL_NM + '\'' +
                ", schoolCode='" + SD_SCHUL_CODE + '\'' +
                ", hsScNm='" + HS_SC_NM + '\'' +
                '}';
    }
}
