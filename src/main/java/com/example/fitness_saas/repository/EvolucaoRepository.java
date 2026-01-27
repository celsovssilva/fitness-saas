package com.example.fitness_saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvolucaoRepository  extends JpaRepository<Evolucao, Long> {
    List<Evolucao> findTop2ByAlunoIdOrderByDataRegistroDesc(Long alunoId);
}
