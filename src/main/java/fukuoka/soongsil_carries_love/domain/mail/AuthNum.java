package fukuoka.soongsil_carries_love.domain.mail;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AuthNum {
    // Key: 사용자 이메일, Value : 인증번호
    // Redis 를 사용하는 대신 자체 메모리를 사용하여 값을 저장한다. 만료기간을 구현하려면 Redis 를 파자.
    public static HashMap<String, Integer> AUTH_NUMBER_MAP;

    public AuthNum() {
        AUTH_NUMBER_MAP = new HashMap<>();
    }
}
