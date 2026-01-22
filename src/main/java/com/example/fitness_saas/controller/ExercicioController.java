package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Exercicio;
import com.example.fitness_saas.service.ExercicioService;
import com.example.fitness_saas.service.IMPL.ExercicioServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exercicio")
public class ExercicioController {

    @Autowired
    ExercicioService exercicioService;

    @PostMapping("/cadastrar")
    public Exercicio salvarExercicio(@RequestBody Exercicio exercicio){
        return exercicioService.salvarExercicio(exercicio);
    }
    @GetMapping("/buscar")
    public List<Exercicio> listarExercicios(){
        return exercicioService.listarExercicios();
    }
    @DeleteMapping("/deletar")
    public void excluirExercicio(@RequestBody Exercicio exercicio){
        exercicioService.excluirExercicio(exercicio);
    }
}


