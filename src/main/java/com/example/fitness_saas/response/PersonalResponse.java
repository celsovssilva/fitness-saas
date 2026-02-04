package com.example.fitness_saas.response;

import com.example.fitness_saas.entity.Personal;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public record PersonalResponse(Long id,
                               String cref,
                               String especialidade,
                               String name,
                               String email,
                               List<AlunoResponse> alunos) {
    public PersonalResponse(Personal personal) {
        this(
                personal.getId(),
                personal.getCref(),
                personal.getEspecialidade(),
                personal.getUser().getName(),
                personal.getUser().getEmail(),
                personal.getAlunos() !=null ? personal.getAlunos().stream().map(AlunoResponse::new).toList() :
                        Collections.emptyList()

        );
}
}
