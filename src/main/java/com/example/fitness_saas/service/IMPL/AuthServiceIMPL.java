package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.dto.LoginDTO;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.service.AuthService;
import com.example.fitness_saas.service.EmailService;
import com.example.fitness_saas.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceIMPL  implements AuthService {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Autowired
    EmailService emailService;

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        var user = userRepository.findByEmail(loginDTO.userName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        

        return null;
    }
}
