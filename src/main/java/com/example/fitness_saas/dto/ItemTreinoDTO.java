package com.example.fitness_saas.dto;

import com.example.fitness_saas.entity.Treino;

public record ItemTreinoDTO(Integer exercicio, Integer series, Integer repeticoes, Integer descanso, String observacao){}
