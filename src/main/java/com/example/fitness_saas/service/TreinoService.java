package com.example.fitness_saas.service;

import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TreinoService {

    public List<TreinoRepository> buscarTreinoPorAluno(Long  idAluno);

    public Treino cadastrarTreino(Treino treino);

}
