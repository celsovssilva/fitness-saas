package com.example.fitness_saas.dto;

public record AlunoRegisterRequest(
        String nome,
        String email,
        String senha,
        Long trainerId,
        Long nutritionistId,
        String objective

){}

