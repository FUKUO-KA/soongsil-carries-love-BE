package fukuoka.soongsil_carries_love.domain.user.service;

import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;

public interface UserService {
    Long save(JoinDto dto);
}
