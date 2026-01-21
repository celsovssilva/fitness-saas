package com.example.fitness_saas.service;

import com.example.fitness_saas.entity.Nutricionista;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NutricionistaService {
    public Nutricionista cadastrarNutricionista(Nutricionista nutricionista);
    public Nutricionista atualizarNutricionista(Nutricionista nutricionista);
    public void deletarNutricionista(Long id);
    public List<Nutricionista> listarNutricionistas();

}
