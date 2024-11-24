package fukuoka.soongsil_carries_love.domain.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private String senderEmail = "fbgyrud828@gmail.com";
    private static int randNum;
    private String receiverEmail;


    // 랜덤으로 숫자 생성
    public static void createRandNumber(){
        randNum = (int)(Math.random() * (90000)) + 100000;
    }

    public MimeMessage CreateMail(String mail){
        createRandNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + randNum + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e){
            e.printStackTrace();
        }

        return message;
    }

    // 이메일 발송
    public int sendMail(String receiverEmail) {

        if (!isValidEmailDomain(receiverEmail)) {
            throw new IllegalArgumentException("허용되지 않은 이메일 도메인입니다.");
        }
        this.receiverEmail = receiverEmail;
        MimeMessage message = CreateMail(receiverEmail);
        javaMailSender.send(message);

        return randNum;
    }

    // 이메일 도메인 검증 메서드
    private boolean isValidEmailDomain(String email) {
        return email.endsWith("@soongsil.ac.kr") || email.endsWith("@ssu.ac.kr");
    }
}
