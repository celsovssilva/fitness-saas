package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    private int series;
    private int repeticoes;
    private String descanso;
    private String observacao;
}