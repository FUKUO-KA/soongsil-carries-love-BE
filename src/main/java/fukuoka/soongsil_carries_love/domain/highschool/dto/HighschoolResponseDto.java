package fukuoka.soongsil_carries_love.domain.highschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HighschoolResponseDto {
    private String highSchoolName;
    private String schoolCode;

    // 기본 생성자 추가
    public HighschoolResponseDto() {
    }

    // 파라미터를 받는 생성자
    public HighschoolResponseDto(String highSchoolName, String schoolCode) {
        this.highSchoolName = highSchoolName;
        this.schoolCode = schoolCode;
    }
}
