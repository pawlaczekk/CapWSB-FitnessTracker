package pl.wsb.fitnesstracker.mail.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

@Service
@RequiredArgsConstructor
@Slf4j
class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    @Override
    public void send(EmailDto email) {
        log.info("Sending email to {}", email.toAddress());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getFrom());
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setText(email.content());
        mailSender.send(message);
        log.info("Email sent successfully to {}", email.toAddress());
    }

}
