package com.example.fitness_saas.dto;

import com.example.fitness_saas.Enum.UserRole;

import java.time.LocalDate;

public record UserDTO(String name, String email, String password, LocalDate dataNascimento , UserRole role){}
