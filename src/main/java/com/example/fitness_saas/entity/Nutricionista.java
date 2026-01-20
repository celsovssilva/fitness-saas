package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_nutricionista")
@Data

public class Nutricionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String crn;

    private String especialidade;

    private String apresentacao;

    @Column(nullable = false)
    private boolean ativo = true;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "nutricionista")
    private List<Aluno> alunos;
}
