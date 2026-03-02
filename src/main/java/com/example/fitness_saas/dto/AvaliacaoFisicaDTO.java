package com.example.fitness_saas.dto;

public record AvaliacaoFisicaDTO(
        Long alunoId,
        Long personalId,
        Double peso,
        Double altura,
        Double massaMuscular,


        Double torax, Double cintura, Double quadril,
        Double bracoEsquerdo, Double bracoDireito,
        Double coxaEsquerda, Double coxaDireita,


        Double dobraSubescapular,
        Double dobraTriceps,
        Double dobraPeitoral,
        Double dobraAxilarMedia,
        Double dobraSupraIliaca,
        Double dobraAbdominal,
        Double dobraCoxa
) {
}
