package fukuoka.soongsil_carries_love.domain.mail.controller;

import fukuoka.soongsil_carries_love.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController implements MailApi{
    private final MailService mailService;
    private int authNumber;     // 이메일 인중 번호 저장하는 변수

    // 인증 이메일 전송
    @PostMapping("/sendAuthNum")
    public HashMap<String, Object> mailSend(@RequestParam String receiverEmail){
        HashMap<String, Object> map = new HashMap<>();

        try{
            authNumber = mailService.sendMail(receiverEmail);
            String num = String.valueOf(authNumber);

            map.put("success", Boolean.TRUE);
            map.put("number", num);
        } catch (Exception e){
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 인증번호 일치여부 확인
    @GetMapping("/checkAuthNum")
    public ResponseEntity<?> mailCheck(@RequestParam String userNumber) {

        boolean isMatch = userNumber.equals(String.valueOf(authNumber));

        return ResponseEntity.ok(isMatch);
    }
}
