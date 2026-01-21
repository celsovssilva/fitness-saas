package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.entity.Nutricionista;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.NutricionistaRepository;
import com.example.fitness_saas.service.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NutricionistaServiceIMPL implements NutricionistaService {
    @Autowired
    private NutricionistaRepository nutricionistaRepository;
    @Override
    public Nutricionista cadastrarNutricionista(Nutricionista nutricionista) {
        return nutricionistaRepository.save(nutricionista);
    }

    @Override
    public Nutricionista atualizarNutricionista(Nutricionista nutricionista) {
         Nutricionista n = nutricionistaRepository.findById(nutricionista.getId()).get();
        n.setCrn(n.getCrn());
        n.setApresentacao(n.getApresentacao());
        n.setEspecialidade(n.getEspecialidade());
        n.setAtivo(n.isAtivo());

        if (n.getUser() != null) {
            User userExistente = n.getUser();
            User userNovosDados = n.getUser();

            userExistente.setName(userNovosDados.getName());
            userExistente.setEmail(userNovosDados.getEmail());

        }
        return nutricionistaRepository.save(n);
    }

    @Override
    public void deletarNutricionista(Long id) {
        nutricionistaRepository.deleteById(id);


    }

    @Override
    public List<Nutricionista> listarNutricionistas() {
        return nutricionistaRepository.findAll();
    }
}
