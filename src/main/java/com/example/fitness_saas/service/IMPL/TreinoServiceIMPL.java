package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.entity.Personal;
import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.repository.AlunoRepository;
import com.example.fitness_saas.repository.PersonalRepository;
import com.example.fitness_saas.service.TreinoService;
import com.example.fitness_saas.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoServiceIMPL implements TreinoService {

    @Autowired
    TreinoRepository treinoRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    PersonalRepository personalRepository;

    @Override
    public List<Treino> buscarTreinoPorAluno(Long idAluno) {
        return treinoRepository.findByAlunoId(idAluno);
    }

    @Override
    public Treino cadastrarTreino(Treino treino) {
        if (treino.getPersonal() == null || treino.getPersonal().getId() == null) {
            throw new RuntimeException("O campo 'personal' com um 'id' válido é obrigatório.");
        }
        if (treino.getAluno() == null || treino.getAluno().getId() == null) {
            throw new RuntimeException("O campo 'aluno' com um 'id' válido é obrigatório.");
        }

        Personal personalDB = personalRepository.findById(treino.getPersonal().getId())
                .orElseThrow(() -> new RuntimeException("Personal não encontrado com o ID fornecido."));

        Aluno alunoDB = alunoRepository.findById(treino.getAluno().getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID fornecido."));


        treino.setPersonal(personalDB);
        treino.setAluno(alunoDB);


        if (treino.getItens() != null) {
            treino.getItens().forEach(item -> item.setTreino(treino));
        }

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

    @Override
    public void deletarTreino(Long id) {
        treinoRepository.deleteById(id);
    }
}
