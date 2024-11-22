package fukuoka.soongsil_carries_love.domain.mail.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public interface MailApi {

    @Operation(summary = "[Mail] 이메일 인증 번호 발송 API", description = "@soongsil.ac.kr, @ssu.ac.kr이 아니면 불가")
    @PostMapping("/sendAuthNum")
    HashMap<String, Object> mailSend(@RequestParam String receiverEmail);

    @Operation(summary = "[Mail] 인증번호 확인 API", description = "responseBody (인증번호 일치 : true / 인증번호 불일치 : false)")
    @GetMapping("/checkAuthNum")
    ResponseEntity<?> mailCheck(@RequestParam String userNumber);
}
