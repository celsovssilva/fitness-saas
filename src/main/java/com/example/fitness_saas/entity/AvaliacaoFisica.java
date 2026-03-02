package com.example.fitness_saas.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAvaliacao;


    private Double peso;
    private Double altura;
    private Double percentualGordura; // Resultado do cálculo de Pollock
    private Double massaMuscular;

    // Circunferências
    private Double torax;
    private Double cintura;
    private Double quadril;
    private Double bracoDireito;
    private Double bracoEsquerdo;
    private Double coxaDireita;
    private Double coxaEsquerda;


    private Double dobraSubescapular;
    private Double dobraTriceps;
    private Double dobraPeitoral;
    private Double dobraAxilarMedia;
    private Double dobraSupraIliaca;
    private Double dobraAbdominal;
    private Double dobraCoxa;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;
}