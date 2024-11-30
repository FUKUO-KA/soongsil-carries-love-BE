package fukuoka.soongsil_carries_love.domain.mail.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MailApi {

    @Operation(summary = "[Mail] 이메일 인증 번호 발송 API", description = "@soongsil.ac.kr, @ssu.ac.kr이 아니면 400, 성공하면 200")
    @PostMapping("/sendAuthNum")
    ResponseEntity<?> mailSend(@RequestParam String receiverEmail);

    @Operation(summary = "[Mail] 인증번호 확인 API", description = "인증번호를 발급하지 않은 이메일이라면 401; 인증번호가 틀렸으면 400; 인증에 통과했으면 200;\n인증 완료하면 메모리에서 삭제됨. 일회용임.")
    @GetMapping("/checkAuthNum")
    ResponseEntity<?> mailCheck(@RequestParam Integer userInputAuthNum, @RequestParam String email);
}
