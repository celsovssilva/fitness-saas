package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino,Long> {
    List<Treino> findByAlunoId(Long alunoId);
}
