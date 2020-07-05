
package com.jjanelle.portfolio.services;

import com.jjanelle.portfolio.models.EmailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendEmailService {

    private final JavaMailSender javaMailSender;
    
    SendEmailService(JavaMailSender mailSender) {
        javaMailSender = mailSender;
    }
    
    public void send(EmailMessage message) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        //simpleMessage.setFrom(String.format("%s <%s>", message.getSenderName(), message.getSenderEmail()));
        simpleMessage.setTo("jonjanelle1@gmail.com");
        simpleMessage.setSubject(message.getSubject());
        simpleMessage.setText(message.getBody());
        javaMailSender.send(simpleMessage);
    }
}
