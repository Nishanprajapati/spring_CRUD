package net.java.springboot_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderService {

    private JavaMailSender mailSender;
    @Autowired
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info.rideease@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);


        mailSender.send(message);
        System.out.println("Email sent Successfully");
    }
}
