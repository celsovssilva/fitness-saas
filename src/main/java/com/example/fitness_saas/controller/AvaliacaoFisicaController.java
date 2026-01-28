package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.AvaliacaoFisicaDTO;
import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import com.example.fitness_saas.service.AvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/avalicaofisica")
public class AvaliacaoFisicaController {
    @Autowired
    private AvaliacaoFisicaService avaliacaoFisicaService;
    @PostMapping("/cadastrar")
    public ResponseEntity<AvaliacaoFisicaResponse> cadastrar(@RequestBody AvaliacaoFisicaDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoFisicaService.cadastrar(request));
    }

    @GetMapping("/aluno/{alunoId}/historico")
    public ResponseEntity<List<AvaliacaoFisicaResponse>> listarHistorico(@PathVariable Long alunoId) {
        return ResponseEntity.ok(avaliacaoFisicaService.buscarHistoricoPorAluno(alunoId));
    }


    @GetMapping("/aluno/{alunoId}/ultima")
    public ResponseEntity<AvaliacaoFisicaResponse> buscarUltima(@PathVariable Long alunoId) {
        return ResponseEntity.ok(avaliacaoFisicaService.buscarUltimaAvaliacao(alunoId));
    }


    @GetMapping("/aluno/{alunoId}/comparar")
    public ResponseEntity<Map<String, Double>> compararEvolucao(@PathVariable Long alunoId) {
        return ResponseEntity.ok(avaliacaoFisicaService.compararEvolucao(alunoId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avaliacaoFisicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
