package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoFisicaRepository  extends JpaRepository<AvaliacaoFisica, Long> {
    List<AvaliacaoFisica> findByAlunoIdOrderByDataAvaliacaoDesc(Long alunoId);


    List<AvaliacaoFisica> findTop2ByAlunoIdOrderByDataAvaliacaoDesc(Long alunoId);
}
