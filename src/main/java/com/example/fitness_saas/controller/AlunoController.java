package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.service.AlunoService;
import com.example.fitness_saas.service.IMPL.PersonalServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/cadastrar")
    public Aluno cadastrarAluno(@RequestBody Aluno aluno){
        userRepository.save(aluno.getUser());
        return alunoService.cadastrarAluno(aluno);

    }

    @PutMapping("/atualizar")
    public Aluno atualizarAluno(@RequestBody Aluno aluno){
        return alunoService.atualizarAluno(aluno);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarAluno(@RequestBody Long id){
         alunoService.deletarAluno(id);
    }

    @GetMapping("/buscar")
    public List<Aluno> buscarAluno(){
        return alunoService.buscarAlunos();
    }
}
