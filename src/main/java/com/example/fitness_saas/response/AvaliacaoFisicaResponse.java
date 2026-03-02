    package com.example.fitness_saas.response;

    import java.time.LocalDate;

    public record AvaliacaoFisicaResponse(
            Long id,
            LocalDate dataAvaliacao,
            String nomeAluno,
            String nomePersonal,
            Double peso,
            Double altura,
            Double imc,
            Double rcq,
            Double percentualGordura,
            Double massaGordaKg,
            Double massaMagraKg,


            Double torax, Double cintura, Double quadril,
            Double bracoEsquerdo, Double bracoDireito,
            Double coxaEsquerda, Double coxaDireita
    ) {}
