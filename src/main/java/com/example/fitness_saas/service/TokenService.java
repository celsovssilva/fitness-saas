package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.AuthDTO;
import com.example.fitness_saas.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String gerarToken(UserDTO userDTO);
    String getSubject(String token);
}
