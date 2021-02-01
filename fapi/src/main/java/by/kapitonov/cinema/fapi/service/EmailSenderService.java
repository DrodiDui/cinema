package by.kapitonov.cinema.fapi.service;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String text);

    void createUserEmail(String email, String password);

}
