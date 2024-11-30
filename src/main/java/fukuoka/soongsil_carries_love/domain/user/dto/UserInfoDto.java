package fukuoka.soongsil_carries_love.domain.user.dto;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
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

    public static UserInfoDto from(User user){
        Highschool highschool = user.getHighschool();

        return UserInfoDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .highSchoolName(highschool.getSchoolName())
                .highSchoolCode(highschool.getSchoolCode())
                .department(user.getDepartment())
                .gender(user.getGender())
                .build();
    }
}
