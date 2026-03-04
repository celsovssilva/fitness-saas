package com.example.fitness_saas.service;

import com.example.fitness_saas.response.AvaliacaoFisicaResponse;

import java.io.ByteArrayInputStream;

public interface PdfService {
    ByteArrayInputStream gerarPdfAvaliacao(AvaliacaoFisicaResponse dados);
}
