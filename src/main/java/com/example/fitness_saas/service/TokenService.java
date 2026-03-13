package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.AuthDTO;
import com.example.fitness_saas.dto.UserDTO;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
       String gerarToken(User user);
       String getSubject(String token);
       String gerarTokenRecuperacao(User user);
}
