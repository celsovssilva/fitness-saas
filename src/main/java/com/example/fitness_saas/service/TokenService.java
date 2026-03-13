package com.example.fitness_saas.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TokenService {
       String gerarToken(User user);
       String getSubject(String token);
       String gerarTokenRecuperacao(Optional<com.example.fitness_saas.entity.User> user);
}
