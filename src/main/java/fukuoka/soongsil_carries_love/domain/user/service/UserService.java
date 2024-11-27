package fukuoka.soongsil_carries_love.domain.user.service;

import fukuoka.soongsil_carries_love.domain.user.dto.UserInfoDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;

public interface UserService {
    User findByEmail(String email);
    UserInfoDto getUserInfo(User user);
}
