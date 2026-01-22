package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.service.IMPL.PersonalServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    @Autowired
    PersonalServiceIMPL personalServiceIMPL;

    @PostMapping("/cadastrar")
    public Aluno cadastrarAluno(@RequestBody Aluno aluno){
        user
        return cadastrarAluno(aluno);

    }

    @PutMapping("/atualizar")
    public Aluno atualizarAluno(@RequestBody Aluno aluno){
        return atualizarAluno(aluno);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarAluno(@RequestBody Long id){
         deletarAluno(id);
    }

    @GetMapping("/buscar")
    public List<Aluno> buscarAluno(){
        return buscarAluno();
    }
}
