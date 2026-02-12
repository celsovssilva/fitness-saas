package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.dto.TreinoDTO;
import com.example.fitness_saas.dto.ItemTreinoDTO;
import com.example.fitness_saas.entity.*;
import com.example.fitness_saas.repository.*;
import com.example.fitness_saas.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinoServiceIMPL implements TreinoService {

    @Autowired
    TreinoRepository treinoRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    ExercicioRepository exercicioRepository;

    @Override
    public List<Treino> buscarTreinoPorAluno(Long idAluno) {
        return treinoRepository.findByAlunoId(idAluno);
    }

    @Override
    @Transactional
    public Treino cadastrarTreino(TreinoDTO treinoDTO) {


        if (treinoDTO.personalId() == null) {
            throw new IllegalArgumentException("O ID do Personal é obrigatório.");
        }
        if (treinoDTO.alunoId() == null) {
            throw new IllegalArgumentException("O ID do Aluno é obrigatório.");
        }

        Personal personalDB = personalRepository.findById(treinoDTO.personalId())
                .orElseThrow(() -> new RuntimeException("Personal não encontrado"));

        Aluno alunoDB = alunoRepository.findById(treinoDTO.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Treino treino = new Treino();
        treino.setNomeTreino(treinoDTO.nomeTreino());
        treino.setPersonal(personalDB);
        treino.setAluno(alunoDB);
        treino.setDataCriacao(LocalDate.now());


        treino = treinoRepository.save(treino);

        if (treinoDTO.itens() != null && !treinoDTO.itens().isEmpty()) {
            Treino finalTreino = treino;

            List<ItemTreino> itens = treinoDTO.itens().stream()

                    .filter(item -> item.exercicio() != null)
                    .map(itemDTO -> {


                        Exercicio exercicio = exercicioRepository.findById(itemDTO.exercicio())
                                .orElseThrow(() -> new RuntimeException(
                                        "Exercício não encontrado com ID: " + itemDTO.exercicio()
                                ));

                        ItemTreino item = new ItemTreino();
                        item.setExercicio(exercicio);
                        item.setSeries(itemDTO.series());
                        item.setRepeticoes(itemDTO.repeticoes());
                        item.setDescanso(itemDTO.descanso());
                        item.setObservacao(itemDTO.observacao());
                        item.setTreino(finalTreino);

                        return item;
                    })
                    .collect(Collectors.toList());


            treino.setItens(itens);
            treino = treinoRepository.save(treino);
        }

        return treino;
    }

    @Override
    @Transactional
    public Treino atualizarTreino(Long id, Treino treino) {
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

        return treinoRepository.save(treinoExistente);
    }

    @Override
    public void deletarTreino(Long id) {
        treinoRepository.deleteById(id);
    }
}