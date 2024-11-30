package fukuoka.soongsil_carries_love.domain.mail.controller;

import fukuoka.soongsil_carries_love.domain.mail.AuthNum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController implements MailApi{

    // 인증 이메일 전송
    @PostMapping("/sendAuthNum")
    public ResponseEntity<?> mailSend(@RequestParam String receiverEmail){

        if (!receiverEmail.endsWith("@soongsil.ac.kr") && !receiverEmail.endsWith("@ssu.ac.kr")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(receiverEmail, HttpStatus.OK);
    }

    // 인증번호 일치여부 확인
    @GetMapping("/checkAuthNum")
    public ResponseEntity<?> mailCheck(@RequestParam Integer userInputAuthNum, @RequestParam String email) {
        // 인증번호를 발급하지 않은 이메일이라면 401
        if (!AuthNum.AUTH_NUMBER_MAP.containsKey(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Integer realAuthNum = AuthNum.AUTH_NUMBER_MAP.get(email);
        if (!realAuthNum.equals(userInputAuthNum)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AuthNum.AUTH_NUMBER_MAP.remove(email);  // 인증완료 하였으므로 삭제
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
