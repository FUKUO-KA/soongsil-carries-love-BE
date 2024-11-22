package fukuoka.soongsil_carries_love.domain.user.dto;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JoinDto {
//    private String name;
    private String email;
    private String password;
    private String college; // 단과대학
    private String department; // 학과/학부
    private String nickname;
    private String studentId; // 학번
    private Gender gender;
    private String schoolCode; // 학교 코드 (요청값으로 사용)
    private LocalDateTime createdAt; // 사용자 지정 생성일 (옵션)
}
