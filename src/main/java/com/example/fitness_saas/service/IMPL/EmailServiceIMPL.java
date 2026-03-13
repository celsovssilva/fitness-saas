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
    public void enviarEmail(String destino, String token) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("celsosilvavinicius151@gmail.com");
            message.setTo(destino);
            message.setSubject("Recuperação de senha");
            message.setText("Use esse token pra redefinir senha:" + token);
            mailSender.send(message);

        } catch (RuntimeException e) {
            throw new RuntimeException("Falha ao enviar email",e);
        }

    }
}
