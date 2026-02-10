package com.example.fitness_saas.response;

import com.example.fitness_saas.entity.Treino;

import java.time.LocalDate;
import java.util.List;


public record TreinoResponse(
        Long id,
        String nomeTreino,
        LocalDate dataCriacao,
        String nomePersonal,
        String nomeAluno,
        List<ItemTreinoResponse> itens
) {
    public TreinoResponse(Treino treino) {
        this(
                treino.getId(),
                treino.getNomeTreino(),
                treino.getDataCriacao(),
                treino.getPersonal().getUser().getName(),
                treino.getAluno().getUser().getName(),
                treino.getItens() != null ?
                        treino.getItens().stream().map(ItemTreinoResponse::new).toList() :
                        List.of()
        );
    }
}
