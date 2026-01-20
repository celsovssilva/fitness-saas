package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pesoInicial;
    private Double altura;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "nutri_id")
    private Nutricionista nutricionista;

}
