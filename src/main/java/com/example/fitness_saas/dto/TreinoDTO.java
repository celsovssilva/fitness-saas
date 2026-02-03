package com.example.fitness_saas.dto;

import java.util.List;

public record TreinoDTO(String nomeTreino, Long personalId, Long alunoId, List<ItemTreinoDTO> itens)  {
}
