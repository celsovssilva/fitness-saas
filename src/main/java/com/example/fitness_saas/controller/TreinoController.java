package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.TreinoDTO;
import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.response.TreinoResponse;
import com.example.fitness_saas.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/treino")
public class TreinoController {

    @Autowired
    TreinoService treinoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<TreinoResponse> cadastrar(@RequestBody TreinoDTO treino){
        Treino treino1= treinoService.cadastrarTreino(treino);
        return ResponseEntity.ok(new TreinoResponse(treino1));
    }

    @GetMapping("/buscar/{idAluno}")
    public ResponseEntity<List<TreinoResponse>> buscar(@PathVariable Long  idAluno){
        List<Treino> treinos = treinoService.buscarTreinoPorAluno(idAluno);
        return ResponseEntity.ok(treinos.stream().map(TreinoResponse::new).toList());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TreinoResponse> atualizar(@PathVariable Long id, @RequestBody Treino treino) {

        Treino treinoAtualizado = treinoService.atualizarTreino(id, treino);
        return ResponseEntity.ok(new TreinoResponse(treinoAtualizado));
    }
}
