package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.LoginDTO;

public interface AuthService {
    LoginDTO login(LoginDTO loginDTO);
}
