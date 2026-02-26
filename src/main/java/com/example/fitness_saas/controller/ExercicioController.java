package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.ExercicioDTO;
import com.example.fitness_saas.entity.Exercicio;
import com.example.fitness_saas.service.ExercicioService;
import com.example.fitness_saas.service.IMPL.ExercicioServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exercicio")
public class ExercicioController {

    @Autowired
    ExercicioService exercicioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Exercicio> salvarExercicio(@RequestBody ExercicioDTO exercicio) {

        Exercicio entidadeSalva = exercicioService.salvarExercicio(exercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadeSalva);
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


