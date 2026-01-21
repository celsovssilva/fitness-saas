package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Personal;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.PersonalRepository;
import com.example.fitness_saas.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonalServiceIMPL  implements PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    @Override
    public Personal cadastrarPersonal(Personal personal) {
        return personalRepository.save(personal);
    }

    @Override
    public Personal atualizarPersonal(Personal personal) {
        Personal p = personalRepository.findById(personal.getId()).get();
        p.setCref(personal.getCref());
        p.setAtivo(personal.isAtivo());
        p.setEspecialidade(personal.getEspecialidade());
        p.setAlunos(personal.getAlunos());

        if(p.getUser()!=null){
            User userExistente = p.getUser();
            User userNovosDados = p.getUser();

            userExistente.setName(userNovosDados.getName());
            userExistente.setEmail(userNovosDados.getEmail());
        }
        return personalRepository.save(p);
    }

    @Override
    public void deletarPersonal(Long id) {
        personalRepository.deleteById(id);
    }

    @Override
    public List<Personal> buscarPersonal() {
        return personalRepository.findAll();
    }
}
