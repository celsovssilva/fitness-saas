package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.AlunoRepository;
import com.example.fitness_saas.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceIMPL implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<Aluno> buscarAlunos() {

        return alunoRepository.findAll();
    }

    @Override
    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno atualizarAluno(Aluno aluno) {
        Aluno a = alunoRepository.findById(aluno.getId()).get();
        a.setAltura(aluno.getAltura());
        a.setPesoInicial(aluno.getPesoInicial());
        a.setNutricionista(aluno.getNutricionista());
        a.setPersonal(aluno.getPersonal());

        if (a.getUser() != null) {
            User userExistente = a.getUser();
            User userNovosDados = a.getUser();

            userExistente.setName(userNovosDados.getName());
            userExistente.setEmail(userNovosDados.getEmail());

        }


        return alunoRepository.save(aluno);

    }

    @Override
    public void deletarAluno(Long id) {
    alunoRepository.deleteById(id);


    }
}
