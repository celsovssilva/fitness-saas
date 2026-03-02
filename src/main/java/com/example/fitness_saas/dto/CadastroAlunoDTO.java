package com.example.fitness_saas.dto;

import java.time.LocalDate;

public record CadastroAlunoDTO(
        String name,
        String email,
        String password,
        Double peso,
        Double altura
       ) {
}
