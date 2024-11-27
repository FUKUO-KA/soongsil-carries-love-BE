package fukuoka.soongsil_carries_love.domain.user.dto;

import fukuoka.soongsil_carries_love.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    String email;
    String nickname;
    String highSchoolName;
    String highSchoolCode;
    String department;
    Gender gender;
}
