package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Evolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataRegistro;
    private Double pesoAtual;
    private Double imc;
    private String fotoUrl;
    private String observacoes;
    @Column(columnDefinition = "TEXT")
    private String parecerProfissional;


    private  boolean StatusEvolucao ;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "registrado_por_id")
    private User registradoPor;
}