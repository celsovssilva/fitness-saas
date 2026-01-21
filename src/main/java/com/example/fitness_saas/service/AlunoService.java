package com.example.fitness_saas.service;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlunoService {
    public List<Aluno> buscarAlunos();
    public Aluno cadastrarAluno(Aluno aluno);
    public Aluno atualizarAluno(Aluno aluno);
    public void deletarAluno(Long id);

}
