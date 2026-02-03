package com.example.fitness_saas.response;

import com.example.fitness_saas.dto.PersonalDTO;

public record AlunoResponse(
        Long id,
        Double pesoInicial,
        Double altura,
        String nome,
        String email,
        PersonalDTO personal
) {
}
