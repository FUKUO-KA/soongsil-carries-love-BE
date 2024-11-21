package fukuoka.soongsil_carries_love.domain.user.repository;

import fukuoka.soongsil_carries_love.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 이메일로 사용자 정보를 가져옴

    Boolean existsByEmail(String email);
}
