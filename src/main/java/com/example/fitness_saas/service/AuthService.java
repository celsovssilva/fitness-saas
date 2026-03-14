package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.LoginDTO;
import com.example.fitness_saas.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginDTO loginDTO);
}
