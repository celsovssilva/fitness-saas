package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.dto.UserDTO;
import com.example.fitness_saas.service.TokenService;
import org.springframework.beans.factory.annotation.Value;

public class TokenServiceIMPL implements TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.issuer}")
    @Override
    public String gerarToken(UserDTO userDTO) {

        return "";
    }

    @Override
    public String getSubject(String token) {
        return "";
    }
}
