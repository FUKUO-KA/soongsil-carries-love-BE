package fukuoka.soongsil_carries_love.domain.user.controller;

import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;
import fukuoka.soongsil_carries_love.domain.user.dto.UserInfoDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import fukuoka.soongsil_carries_love.domain.user.service.JoinService;
import fukuoka.soongsil_carries_love.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi{

    private final JoinService joinService;
    private final UserService userService;

    @PostMapping("/join")   // 회원가입
    public ResponseEntity<String> joinUser(@ModelAttribute JoinDto joinDto) {
        joinService.joinProcess(joinDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestHeader("email") String email){
        User user = userService.findByEmail(email);

        UserInfoDto userInfo = userService.getUserInfo(user);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/gender-stats")
    public void getGenderStats(@RequestHeader("email") String email) {
        User user = userService.findByEmail(email);
        return ;
    }

    @GetMapping("/hakbeon-stats")
    public void getHakbeonStats(@RequestHeader("email") String email) {

    }
}
