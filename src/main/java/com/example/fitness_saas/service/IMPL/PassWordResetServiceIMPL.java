package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.PassWordReset;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.PassWordTokenRepository;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.service.EmailService;
import com.example.fitness_saas.service.PassWordResetService;
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
        String corpo = "Olá " + user.getName() + ",\n\n" +
                "Você solicitou a redefinição de sua senha. " +
                "Clique no link abaixo para criar uma nova senha:\n" +
                link + "\n\n" +
                "Este link expira em 15 minutos.";

        emailService.enviarEmail(user.getEmail(), "Recuperação De Senha", corpo);
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
}
