package fukuoka.soongsil_carries_love.domain.mail.util;

import fukuoka.soongsil_carries_love.domain.mail.AuthNum;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSendUtil {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "fbgyrud828@gmail.com";

    @Async
    public void sendMail(String mail) {
        MimeMessage message = javaMailSender.createMimeMessage();

        Integer randNum = (int)(Math.random() * (90000)) + 100000;
        AuthNum.AUTH_NUMBER_MAP.put(mail, randNum);

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + randNum + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
