package fukuoka.soongsil_carries_love.domain.mail;

import fukuoka.soongsil_carries_love.domain.mail.util.MailSendUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class MailAspect {
    private final MailSendUtil mailSendUtil;

    @AfterReturning(
            pointcut = "execution(* fukuoka.soongsil_carries_love.domain.mail.controller.MailController.mailSend(String))",
            returning = "returned"
    )
    public void afterReturnMailSend(ResponseEntity<?> returned){
        String email = (String) Objects.requireNonNull(returned.getBody());
        mailSendUtil.sendMail(email);
    }
}
