package fukuoka.soongsil_carries_love.domain.user.entity;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter

public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(length = 20)
    private String name;    // 이름

    @NotNull
    private String studentId;   // 학번

    @NotNull
    private String nickname;    // 닉네임

    private String college;     // 단과대학

    private String department;  // 학과

    @NotNull
    @Column(name = "email", length = 50, unique = true)
    private String email;       // 이메일

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;      // 성별

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;    // 비밀번호

    // N:1 관계 설정 (User:Highschool)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highschool_id")
    private Highschool highschool;  // 사용자가 속한 고등학교

    @NotNull
    @Column(name = "school_code", length = 20)
    private String schoolCode;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "role", length = 50)
    private String role = "USER";
}
