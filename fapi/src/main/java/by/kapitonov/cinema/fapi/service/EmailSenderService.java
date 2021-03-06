package by.kapitonov.cinema.fapi.service;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String text);

    void createUserEmail(String email, String password);

    void sendNotification(String email);

    void sendActivationEmail(String email, String activationCode);
}
