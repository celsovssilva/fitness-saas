package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.entity.Personal;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.AlunoRepository;
import com.example.fitness_saas.repository.PersonalRepository;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceIMPL implements AlunoService{

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonalRepository personalRepository;

    @Override
    public List<Aluno> buscarAlunos() {

        return alunoRepository.findAll();
    }

    @Override
    public Aluno cadastrar(Aluno aluno) {

        if (aluno.getPersonal() != null && aluno.getPersonal().getId() != null) {

            Personal personalCompleto = personalRepository.findById(aluno.getPersonal().getId())
                    .orElseThrow(() -> new RuntimeException("Personal não encontrado"));


            aluno.setPersonal(personalCompleto);
        }
        return alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno atualizarAluno(Aluno aluno) {

        Aluno a = alunoRepository.findById(aluno.getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + aluno.getId()));

        a.setAltura(aluno.getAltura());
        a.setPesoInicial(aluno.getPesoInicial());
        a.setPersonal(aluno.getPersonal());


        if (aluno.getUser() != null && a.getUser() != null) {
            User userExistente = a.getUser();
            User userNovosDados = aluno.getUser();

            userExistente.setName(userNovosDados.getName());
            userExistente.setEmail(userNovosDados.getEmail());
            userExistente.setDataNascimento(userNovosDados.getDataNascimento());

        }

        return alunoRepository.save(a);
    }

    @Override
    public void deletarAluno(Long id) {
    alunoRepository.deleteById(id);


    }
    @Override
    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }
}


