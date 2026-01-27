package com.example.fitness_saas.dto;

public record TreinoDTO(String nomeTreino, Long personalId, Long alunoId, List<ItemTreinoRequest> itens)  {
}
