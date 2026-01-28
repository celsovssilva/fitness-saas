package com.example.fitness_saas.dto;

public record AvaliacaoFisicaDTO(
        Long alunoId,
        Long personalId,
        Double peso,
        Double altura,
        Double percentualGordura,
        Double massaMuscular,

        Double torax,
        Double cintura,
        Double quadril,
        Double bracoEsquerdo,
        Double bracoDireito,
        Double coxaEsquerda,
        Double coxaDireita
) {
}
