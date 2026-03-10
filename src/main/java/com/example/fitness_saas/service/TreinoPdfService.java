package com.example.fitness_saas.service;

import com.example.fitness_saas.response.TreinoResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public interface TreinoPdfService {
    ByteArrayInputStream gerarTreinoPDF(TreinoResponse treino) ;
}
