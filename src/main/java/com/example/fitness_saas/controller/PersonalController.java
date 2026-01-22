package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Personal;
import com.example.fitness_saas.service.IMPL.PersonalServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personal")
public class PersonalController {
    @Autowired
    PersonalServiceIMPL personalServiceIMPL;

    @PostMapping("/cadastrar")
    public Personal cadastrarPersonal(@RequestBody Personal personal){
        return personalServiceIMPL.cadastrarPersonal(personal);
    }

    @PutMapping("/atualizar")
    public Personal atualizarPersonal(@RequestBody Personal personal){
        return personalServiceIMPL.atualizarPersonal(personal);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPersonal(@PathVariable Long id){
        personalServiceIMPL.deletarPersonal(id);
    }

    @GetMapping("/buscar")
    public List<Personal> buscarPersonal(){
        return personalServiceIMPL.buscarPersonal();
    }

}
