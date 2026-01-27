package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.example.fitness_saas.entity.User;
@Entity
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pesoInicial;
    private Double altura;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;



}
