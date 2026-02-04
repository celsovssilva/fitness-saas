package com.example.fitness_saas.response;

import com.example.fitness_saas.dto.PersonalDTO;
import com.example.fitness_saas.entity.Aluno;

import java.util.Collections;
import java.util.List;

public record AlunoResponse(
        Long id,
        Double pesoInicial,
        Double altura,
        String nome,
        String email,
        PersonalDTO personal
) {
    public AlunoResponse(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getPesoInicial(),
                aluno.getAltura(),
                aluno.getUser().getName(),
                aluno.getUser().getEmail(),
                aluno.getPersonal() != null ? new PersonalDTO (
                        aluno.getPersonal().getId(),
                        aluno.getPersonal().getCref(),
                        aluno.getPersonal().getEspecialidade(),
                        aluno.getPersonal().getUser().getName()
                ) : null);
    }
}
