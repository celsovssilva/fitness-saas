package com.example.fitness_saas.service;

import com.example.fitness_saas.dto.AvaliacaoFisicaDTO;
import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.repository.AlunoRepository;
import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AlunoService {
    public interface AvaliacaoFisicaService {
        AvaliacaoFisicaResponse cadastrar(AvaliacaoFisicaDTO request);
        List<AvaliacaoFisicaResponse> buscarHistorico(Long alunoId);
        AvaliacaoFisicaResponse buscarUltima(Long alunoId);

        Map<String, Double> compararEvolucao(Long alunoId);
    }

}
