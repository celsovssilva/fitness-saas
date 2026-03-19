package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceIMPL implements EmailService {

   @Autowired
   private EmailService emailService;
   @Autowired
   private JavaMailSender mailSender;

    @Override
    public void enviarEmail(String destino, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("celsosilvavinicius151@gmail.com");
        message.setTo(destino);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);


    }
}
