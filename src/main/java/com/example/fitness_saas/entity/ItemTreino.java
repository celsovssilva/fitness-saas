package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    private Integer series;
    private Integer repeticoes;
    private Integer descanso;
    private String observacao;
}