package com.example.fitness_saas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor

public class PassWordReset { // redefinir senha por token

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token; // gera o token

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // associa o token com relação de um pra um(um token por user)

    @Column
    private LocalDateTime expiration;// expiração

    public PassWordReset(String token, User user, LocalDateTime expiration) {
        this.token = token;
        this.user = user;
        this.expiration = LocalDateTime.now().plusMinutes(15);//expira em 15m
    }


    public PassWordReset(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiration = LocalDateTime.now().plusMinutes(15);
    }


}
