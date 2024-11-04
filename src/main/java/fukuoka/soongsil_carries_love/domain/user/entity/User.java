package fukuoka.soongsil_carries_love.domain.user.entity;

import fukuoka.soongsil_carries_love.common.BaseEntity;
import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
public class User extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;    // 이름

    @Column(nullable = false)
    private String studentId;   // 학번

    @Column(nullable = false)
    private String nickname;    // 닉네임

    @Column(nullable = false)
    private String college;     // 단과대학

    @Column(nullable = false)
    private String department;  // 학과

    @Column(nullable = false, length = 50, unique = true)
    private String email;       // 이메일

    @Column(nullable = false)
    private Gender gender;      // 성별

    @Column(nullable = false)
    private String password;    // 비밀번호

    private LocalDateTime lastLoginAt;  // 마지막 로그인 일시

    // N:1 관계 설정 (User:Highschool)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highschool_id")
    private Highschool highschool;  // 사용자가 속한 고등학교
}
