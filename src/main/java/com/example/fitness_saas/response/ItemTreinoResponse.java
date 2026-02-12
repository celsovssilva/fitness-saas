package com.example.fitness_saas.response;

import com.example.fitness_saas.entity.ItemTreino;

public record ItemTreinoResponse(
        Long id,
        String exercicioNome,
        int series,
        int repeticoes,
        int descanso,
        String observacao

) {
    public ItemTreinoResponse(ItemTreino item) {
        this(
                item.getId(),
                item.getExercicio().getNome(),
                item.getSeries(),
                item.getRepeticoes(),
                item.getDescanso(),
                item.getObservacao()
        );
    }
}