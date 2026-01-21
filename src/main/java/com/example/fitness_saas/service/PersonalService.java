package com.example.fitness_saas.service;


import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.entity.Personal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalService  {
    public Personal cadastrarPersonal(Personal personal);
    public Personal atualizarPersonal(Personal personal);
    public void deletarPersonal(int id);
    public List<Personal> buscarPersonal();
}
