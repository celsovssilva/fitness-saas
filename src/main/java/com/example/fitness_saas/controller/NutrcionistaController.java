package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Nutricionista;
import com.example.fitness_saas.service.IMPL.NutricionistaServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/nuntrcionista")
public class NutrcionistaController {

    @Autowired
    NutricionistaServiceIMPL nutricionistaServiceIMPL;

    @PostMapping("/cadastrar")
    public Nutricionista cadastrarNutricionista(Nutricionista nutricionista){
       return nutricionistaServiceIMPL.cadastrarNutricionista(nutricionista);
    }

    @PutMapping("/atualizar")
    public Nutricionista atualizarNutricionista(Nutricionista nutricionista){
        return nutricionistaServiceIMPL.atualizarNutricionista(nutricionista);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarNutricionista(Long id){
        nutricionistaServiceIMPL.deletarNutricionista( id);
    }

    @GetMapping("/buscar")
    public List<Nutricionista> buscarNutricionista(){
        return nutricionistaServiceIMPL.listarNutricionistas();
    }
}
