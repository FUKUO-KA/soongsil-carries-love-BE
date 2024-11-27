package fukuoka.soongsil_carries_love.domain.highschool.repository;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HighschoolRepository extends JpaRepository<Highschool, Long> {

    Optional<Highschool> findBySchoolCode(String schoolCode);

    @Query("SELECT h.schoolCode FROM Highschool h")
    List<String> findAllSchoolCodes();

    @Query("SELECT u.studentId FROM User u WHERE u.highschool.schoolCode = :highschoolCode")
    List<String> findStudentIdsByHighschoolCode(@Param("highschoolCode") String highschoolCode);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.highschool.schoolCode = :highschoolCode")
    boolean existsByHighschoolCode(@Param("highschoolCode") String highschoolCode);

    @Query("SELECT u.highschool.schoolName AS schoolName, COUNT(u) AS userCount " +
            "FROM User u " +
            "GROUP BY u.highschool.schoolName " +
            "ORDER BY userCount DESC")
    List<Object[]> findUserCountByHighschool();
}
