package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.Config.RabbiMQConfig;
import com.example.fitness_saas.entity.PassWordReset;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.PassWordTokenRepository;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.service.EmailService;
import com.example.fitness_saas.service.PassWordResetService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PassWordResetServiceIMPL implements PassWordResetService {

    @Autowired
    private PassWordTokenRepository passWordTokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public String createPassWord(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        String token = UUID.randomUUID().toString();
        PassWordReset passWordReset = new PassWordReset(token, user);
        passWordTokenRepository.save(passWordReset);

        String link = "http://localhost:8080/api/auth/reset-password?token=" + token;

        sendToQueue(user.getEmail(), link);
        return "email enviado com sucesso!" + email;
    }

    @Override
    public String validatePassWord(String token, String newPassword) {
        PassWordReset passWordReset = passWordTokenRepository.findByToken(token);
        if (passWordReset == null) {
            return "token invalido";
        }

        if (!passWordReset.getToken().equals(token)) {
            return "token invalido";
        }

        User user = passWordReset.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passWordTokenRepository.delete(passWordReset);
        return "Senha alterada com sucesso!";
    }

    //
    private void sendToQueue(String email,String link){
        String message = "enviar email para " + email + "| link: " + link;

        rabbitTemplate.convertAndSend(RabbiMQConfig.QUEUE_NAME, message);
    }

}
