package fukuoka.soongsil_carries_love.domain.highschool.repository;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HighschoolRepository extends JpaRepository<Highschool, Long> {

    Optional<Highschool> findBySchoolCode(String schoolCode);

    @Query("SELECT h.schoolCode FROM Highschool h")
    List<String> findAllSchoolCodes();

    @Query("SELECT h.schoolName FROM Highschool h")
    List<String> findAllSchoolNames();
}
