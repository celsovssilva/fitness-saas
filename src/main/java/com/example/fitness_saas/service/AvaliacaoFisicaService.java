package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.AvaliacaoFisicaDTO;
import com.example.fitness_saas.entity.AvaliacaoFisica;
import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AvaliacaoFisicaService {
    AvaliacaoFisicaResponse cadastrar(AvaliacaoFisicaDTO request);


    List<AvaliacaoFisicaResponse> buscarHistoricoPorAluno(Long alunoId);

    AvaliacaoFisicaResponse buscarUltimaAvaliacao(Long alunoId);
    Map<String, Double> compararEvolucao(Long alunoId);
    void deletar(Long id);




}
