package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.PersonalDTO;
import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.repository.UserRepository;
import com.example.fitness_saas.response.AlunoResponse;
import com.example.fitness_saas.service.AlunoService;
import com.example.fitness_saas.service.IMPL.PersonalServiceIMPL;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/cadastrar")
    public Aluno cadastrarAluno(@RequestBody Aluno aluno){
        userRepository.save(aluno.getUser());
        return alunoService.cadastrar(aluno);

    }

    @PutMapping("/atualizar")
    public Aluno atualizarAluno(@RequestBody Aluno aluno){
        return alunoService.atualizarAluno(aluno);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlunoResponse> buscarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarAlunoPorId(id);

        AlunoResponse response = new AlunoResponse(
                aluno.getId(),
                aluno.getPesoInicial(),
                aluno.getAltura(),
                aluno.getUser().getName(),
                aluno.getUser().getEmail(),
                aluno.getPersonal() != null ? new PersonalDTO(
                        aluno.getPersonal().getId(),
                        aluno.getPersonal().getUser().getName(),
                        aluno.getPersonal().getCref()
                ) : null
        );

      return ResponseEntity.ok(response);
    }
}
