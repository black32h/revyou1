package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private final String emailFrom = "blackspetya@gmail.com"; // Ваша електронна адреса
    private final String password = "your password"; // Ваш пароль програми

    // Метод для надсилання електронного листа
    public void sendEmail(String recipient, String subject, String messageBody) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true"); // Додати для відладки

        // Створення сесії з авторизацією
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });

        // Створення та надсилання повідомлення
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailFrom));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(messageBody);

        // Надсилаємо електронний лист
        try {
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace(); // Друкуємо стек помилок
            throw e; // Можна також обробити по-іншому
        }
    }
}
