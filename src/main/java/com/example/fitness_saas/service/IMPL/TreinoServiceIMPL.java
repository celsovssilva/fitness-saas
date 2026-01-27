package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.service.TreinoService;
import com.example.fitness_saas.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoServiceIMPL implements TreinoService {

    @Autowired
    TreinoRepository treinoRepository;

    @Override
    public List<Treino> buscarTreinoPorAluno(Long idAluno) {
        return treinoRepository.findByAlunoId(idAluno);
    }

    @Override
    public Treino cadastrarTreino(Treino treino) {
        return treinoRepository.save(treino);
    }

    @Override
    public Treino atualizarTreino(Long id ,Treino treino) {
        Treino treinoExistente = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));


        treinoExistente.setNomeTreino(treino.getNomeTreino());

        treinoExistente.getItens().clear();

        if (treino.getItens() != null) {
            treino.getItens().forEach(item -> {
                item.setTreino(treinoExistente);
                treinoExistente.getItens().add(item);
            });
        }
        return treinoRepository.save(treino);
    }
}
