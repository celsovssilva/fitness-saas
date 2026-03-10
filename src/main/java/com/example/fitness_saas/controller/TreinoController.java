package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.TreinoDTO;
import com.example.fitness_saas.entity.Treino;
import com.example.fitness_saas.repository.TreinoRepository;
import com.example.fitness_saas.response.TreinoResponse;
import com.example.fitness_saas.service.TreinoPdfService;
import com.example.fitness_saas.service.TreinoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/treino")
public class TreinoController {

    @Autowired
    TreinoService treinoService;
    @Autowired
    TreinoRepository treinoRepository;
    @Autowired
    TreinoPdfService treinoPdfService;

    @PostMapping("/cadastrar")
    public ResponseEntity<TreinoResponse> cadastrar(@RequestBody TreinoDTO treino) {
        Treino treino1 = treinoService.cadastrarTreino(treino);
        return ResponseEntity.ok(new TreinoResponse(treino1));
    }

    @GetMapping("/buscar/{idAluno}")
    public ResponseEntity<List<TreinoResponse>> buscar(@PathVariable Long idAluno) {
        List<Treino> treinos = treinoService.buscarTreinoPorAluno(idAluno);
        return ResponseEntity.ok(treinos.stream().map(TreinoResponse::new).toList());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TreinoResponse> atualizar(@PathVariable Long id, @RequestBody Treino treino) {

        Treino treinoAtualizado = treinoService.atualizarTreino(id, treino);
        return ResponseEntity.ok(new TreinoResponse(treinoAtualizado));
    }

    @GetMapping("/{id}/pdf")
    @Transactional
    public ResponseEntity<byte[]> baixarTreinoPdf(@PathVariable Long id) {

        Treino treino = treinoRepository.findTreinoCompletoParaPdf(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        TreinoResponse dados = new TreinoResponse(treino);


        ByteArrayInputStream pdfStream = treinoPdfService.gerarTreinoPDF(dados);

        byte[] pdfBytes = pdfStream.readAllBytes();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);


        headers.setContentDisposition(ContentDisposition.inline()
                .filename("Treino_" + dados.nomeAluno().replace(" ", "_") + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

    }

}
