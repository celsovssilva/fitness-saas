package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceIMPL implements EmailService {


   @Autowired
   private JavaMailSender mailSender;

    @Override
    public void enviarEmail(String destino, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();//chama a classe de envio
        message.setFrom("celsosilvavinicius151@gmail.com");//chama o email que vai enviar
        message.setTo(destino);//chama o objeto de envio
        message.setSubject(subject);//chama o assunto do email
        message.setText(body);// o corpo do email

        mailSender.send(message);// envia


    }

}
