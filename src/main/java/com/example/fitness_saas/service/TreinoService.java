package com.example.fitness_saas.service;

import com.example.fitness_saas.entity.Treino;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TreinoService {

    public List<Treino> buscarTreinoPorAluno(Long idAluno);

    public Treino cadastrarTreino(Treino treino);

    public Treino atualizarTreino(Long id,Treino treino);

    public void deletarTreino(Long id);
}
