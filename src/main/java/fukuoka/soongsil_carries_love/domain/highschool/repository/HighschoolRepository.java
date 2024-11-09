package fukuoka.soongsil_carries_love.domain.highschool.repository;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HighschoolRepository extends JpaRepository<Highschool, Long> {
    Optional<Highschool> findBySchoolCode(String schoolCode);

    void deleteBySchoolCode(String schoolCode);

}
