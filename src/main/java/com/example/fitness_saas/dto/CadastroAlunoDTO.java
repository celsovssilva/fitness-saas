package com.example.fitness_saas.dto;

public record CadastroAlunoDTO(
        String name,
        String email,
        String password,
        Double peso,
        Double altura) {
}
