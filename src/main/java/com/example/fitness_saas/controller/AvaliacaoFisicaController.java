package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.AvaliacaoFisicaDTO;
import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import com.example.fitness_saas.response.EvolucaoResponse;
import com.example.fitness_saas.service.AlunoService;
import com.example.fitness_saas.service.AvaliacaoFisicaService;
import com.example.fitness_saas.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/avaliacaofisica")
public class AvaliacaoFisicaController {
    @Autowired
    private AvaliacaoFisicaService avaliacaoFisicaService;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private AlunoService alunoService;
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

    @GetMapping("/{id}/pdf")
    public ResponseEntity<InputStreamResource> pdfAvaliacao(@PathVariable Long id) {
        AvaliacaoFisicaResponse a = avaliacaoFisicaService.buscarAvaliacaoPorId(id);
        ByteArrayInputStream bis = pdfService.gerarPdfAvaliacao(a);
        String nomeArquivo = "Avaliacao_" + a.nomeAluno().replace(" ", "_") + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nomeArquivo);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }
    @PostMapping(value = "/importarExcel/{alunoId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EvolucaoResponse> compararEvolucaoExcel(@PathVariable Long alunoId,@RequestParam("file") MultipartFile file ) throws IOException {
            EvolucaoResponse    response = avaliacaoFisicaService.compararSistemaContraExcel(alunoId,file);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

