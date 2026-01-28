package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAvaliacao;


    private Double peso;
    private Double altura;
    private Double percentualGordura;
    private Double massaMuscular;


    private Double torax;
    private Double cintura;
    private Double quadril;
    private Double bracoDireito;
    private Double bracoEsquerdo;
    private Double coxaDireita;
    private Double coxaEsquerda;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;
}
