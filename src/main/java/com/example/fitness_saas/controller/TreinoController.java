package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Treino> atualizar(@PathVariable Long id, @RequestBody Treino treino) {

        Treino treinoAtualizado = treinoService.atualizarTreino(id, treino);
        return ResponseEntity.ok(treinoAtualizado);
    }
}
