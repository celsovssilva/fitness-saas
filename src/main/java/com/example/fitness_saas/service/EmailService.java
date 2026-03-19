package com.example.fitness_saas.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void enviarEmail(String destino,String subject,String body);
}
