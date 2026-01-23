package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/treino")
public class TreinoController {

    @Autowired
    TreinoService treinoService;

    @PostMapping("/cadastrar")
    public Treino cadastrar(Treino treino){
        return treinoService.cadastrarTreino(treino);
    }

    @GetMapping("/buscar")
    public List<Treino> buscar(Long  idAluno){
        return treinoService.buscarTreinoPorAluno(idAluno);
    }
}
