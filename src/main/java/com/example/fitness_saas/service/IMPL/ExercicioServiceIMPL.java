package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Exercicio;
import com.example.fitness_saas.repository.ExercicioRepository;
import com.example.fitness_saas.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioServiceIMPL implements ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Override
    public Exercicio salvarExercicio(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    @Override
    public List<Exercicio> listarExercicios() {
        return exercicioRepository.findAll();
    }

    @Override
    public void excluirExercicio(Exercicio exercicio) {
            exercicioRepository.delete(exercicio);
    }
}
