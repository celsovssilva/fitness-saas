package com.example.fitness_saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<TreinoRepository,Long> {
    List<TreinoRepository> findByAlunoId(Long alunoId);
}
