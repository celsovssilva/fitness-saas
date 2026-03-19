package com.example.fitness_saas.service;

import com.example.fitness_saas.entity.PassWordReset;
import org.springframework.stereotype.Service;

@Service
public interface PassWordResetService {
    public String createPassWord(String email);
    public String validatePassWord(String token, String newPassword);
}
