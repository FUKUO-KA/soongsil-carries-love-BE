package fukuoka.soongsil_carries_love.domain.highschool.repository;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HighschoolRepository extends JpaRepository<Highschool, Long> {
    @Query("SELECT h.schoolCode FROM Highschool h")
    List<String> findAllSchoolCodes();
}
