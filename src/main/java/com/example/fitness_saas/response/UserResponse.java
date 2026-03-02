package com.example.fitness_saas.response;

import com.example.fitness_saas.Enum.UserRole;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String name,
        String email,
        LocalDate dataNascimento,
        String sexo,
        UserRole role
) {
}
