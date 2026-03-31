package com.example.fitness_saas.dto;

import java.io.Serializable;

public record TreinoEmailDTO(
        String emailAluno,
        String nomeAluno,
        String nomePersonal,
        String tipoTreino
) {

}
