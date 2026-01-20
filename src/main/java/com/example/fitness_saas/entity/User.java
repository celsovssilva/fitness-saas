package com.example.fitness_saas.entity;

import com.example.fitness_saas.Enum.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}