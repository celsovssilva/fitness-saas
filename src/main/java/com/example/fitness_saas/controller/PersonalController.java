package com.example.fitness_saas.controller;

import com.example.fitness_saas.entity.Personal;
import com.example.fitness_saas.response.PersonalResponse;
import com.example.fitness_saas.service.IMPL.PersonalServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personal")
public class PersonalController {

    @Autowired
    private PersonalServiceIMPL personalServiceIMPL;

    @PostMapping("/cadastrar")
    public ResponseEntity<PersonalResponse> cadastrarPersonal(@RequestBody Personal personal) {
        Personal novoPersonal = personalServiceIMPL.cadastrarPersonal(personal);

        return ResponseEntity.ok(new PersonalResponse(novoPersonal));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<PersonalResponse> atualizarPersonal( @RequestBody Personal personal) {

        Personal personalAtualizado = personalServiceIMPL.atualizarPersonal( personal);
        return ResponseEntity.ok(new PersonalResponse(personalAtualizado));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPersonal(@PathVariable Long id) {
        personalServiceIMPL.deletarPersonal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PersonalResponse>> buscarPersonal() {
        List<Personal> personals = personalServiceIMPL.buscarPersonal();
        List<PersonalResponse> response = personals.stream()
                .map(PersonalResponse::new)
                .toList();

        return ResponseEntity.ok(response);
    }
}
