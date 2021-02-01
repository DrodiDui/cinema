package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    @Override
    public void createUserEmail(String email, String password) {

        String subject = "User activation";
        String message = String.format(
                "Your email: %s\nYour password: %s\n",
                email,
                password
        );

        sendEmail(email, subject, message);
    }

    @Override
    public void sendNotification(String email) {
        String subject = "Notification";
        String message = String.format("You have successfully booked your tickets");

        sendEmail(email, subject, message);
    }
}
