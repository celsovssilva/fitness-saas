package com.example.fitness_saas.service;

public interface EmailService {
    void enviarEmail(String destino,String subject,String body);
}
