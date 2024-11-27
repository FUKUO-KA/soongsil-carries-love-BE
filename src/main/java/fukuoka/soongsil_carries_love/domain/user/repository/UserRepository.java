package fukuoka.soongsil_carries_love.domain.user.repository;

import fukuoka.soongsil_carries_love.domain.user.entity.User;
import fukuoka.soongsil_carries_love.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    User findByEmail(String email);

    // 특정 highschoolCode로 등록된 유저 수 조회
    long countByHighschool_SchoolCode(String highschoolCode);

    // 특정 highschoolCode에 속한 성별별 유저 수를 조회
    int countByHighschool_SchoolCodeAndGender(String highschoolCode, Gender gender);
}
