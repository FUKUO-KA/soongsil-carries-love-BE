package fukuoka.soongsil_carries_love.domain.highschool.entity;

import fukuoka.soongsil_carries_love.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
public class Highschool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String schoolName;  // 고등학교 이름

    @Column(nullable = false, unique = true, length = 20)
    private String schoolCode;      // 고유 학교 코드 (DynamoDB의 room_id로 사용 가능)

    @Column(nullable = false, length = 4)
    private String coeduType;       // 남녀공학구분(남/녀/남녀공학)

    // 1:N 관계 설정
    @OneToMany(mappedBy = "highschool")
    private List<User> users;       // 고등학교에 속한 사용자들
}
