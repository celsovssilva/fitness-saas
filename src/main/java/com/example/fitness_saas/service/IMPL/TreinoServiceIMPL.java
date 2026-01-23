package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.service.TreinoService;
import com.example.fitness_saas.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TreinoServiceIMPL implements TreinoService {

    @Autowired
    TreinoRepository treinoRepository;

    @Override
    public List<TreinoRepository> buscarTreinoPorAluno(Long idAluno) {
        return treinoRepository.findByAlunoId(idAluno);
    }

    @Override
    public Treino cadastrarTreino(Treino treino) {

        return treinoRepository.save(treino);
    }
}
