package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.ExercicioDTO;
import com.example.fitness_saas.entity.Exercicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExercicioService {
    Exercicio salvarExercicio(ExercicioDTO exercicio);
    List<Exercicio> listarExercicios();
    void  excluirExercicio(Exercicio exercicio);
}
