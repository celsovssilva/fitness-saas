package com.example.fitness_saas.response;

import java.util.Map;

public record EvolucaoResponse(

        String aluno,
        AvaliacaoFisicaResponse atual,
        AvaliacaoFisicaResponse antiga,
        Map<String, Double> diferencas

) {
}
