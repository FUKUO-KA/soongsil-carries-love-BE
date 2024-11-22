package fukuoka.soongsil_carries_love.domain.user.converter;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserConverter {

    public User toEntity(JoinDto joinDto, String encodedPassword, Highschool highschool) {
        return User.builder()
//                .name(joinDto.getName())      // 이름 안받음
                .email(joinDto.getEmail())
                .college(joinDto.getCollege() != null ? joinDto.getCollege() : "") // 단과대학 (기본값: 빈 문자열)
                .department(joinDto.getDepartment() != null ? joinDto.getDepartment() : "") // 학과/학부 (기본값: 빈 문자열)
                .nickname(joinDto.getNickname())        // 닉네임
                .password(encodedPassword) // 암호화된 패스워드 사용
                .gender(joinDto.getGender())
                .studentId(joinDto.getStudentId()) // 학번
                .highschool(highschool)
                .schoolCode(highschool.getSchoolCode())
                .isDeleted(false) // 초기값 false
                .createdAt(joinDto.getCreatedAt() != null ? joinDto.getCreatedAt() : LocalDateTime.now()) // 현재 시각
                .build();
    }
}
