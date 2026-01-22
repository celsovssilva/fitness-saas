package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.Personal;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.PersonalRepository;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceIMPL  implements PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Personal cadastrarPersonal(Personal personal) {
        userRepository.save(personal.getUser());
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
