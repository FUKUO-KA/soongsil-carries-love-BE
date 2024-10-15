package fukuoka.soongsil_carries_love.domain.highschool.entity;

import fukuoka.soongsil_carries_love.common.BaseEntity;
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
public class Highschool extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String highSchoolName;  // 고등학교 이름

    @Column(nullable = false, length = 255)
    private String locationName;    // 시도명

    // 1:N 관계 설정
    @OneToMany(mappedBy = "highschool")
    private List<User> users;       // 고등학교에 속한 사용자들
}
