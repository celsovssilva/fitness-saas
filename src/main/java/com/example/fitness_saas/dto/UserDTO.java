package com.example.fitness_saas.dto;

import com.example.fitness_saas.Enum.UserRole;

public record UserDTO(String name, String email, String password, UserRole role){}
