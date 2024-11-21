package fukuoka.soongsil_carries_love.domain.user.converter;

import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserConverter {

    public User toEntity(JoinDto joinDto, String encodedPassword) {
        return User.builder()
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .college(joinDto.getCollege())
                .department(joinDto.getDepartment())
                .nickname(joinDto.getNickname())
                .password(encodedPassword) // 암호화된 패스워드 사용
                .gender(joinDto.getGender())
                .studentId(joinDto.getStudentId())
                .highschool(joinDto.getHighschoolId()) // Highschool 객체 매핑
                .isDeleted(false) // 초기값 false
                .createdAt(joinDto.getCreatedAt() != null ? joinDto.getCreatedAt() : LocalDateTime.now()) // 현재 시각
                .build();
    }
}
