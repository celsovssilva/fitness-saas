package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreinoRepository extends JpaRepository<Treino,Long> {
    List<Treino> findByAlunoId(Long alunoId);
    @Query("SELECT t FROM Treino t LEFT JOIN FETCH t.itens i LEFT JOIN FETCH i.exercicio WHERE t.id = :id")
    Optional<Treino> findTreinoCompletoParaPdf(@Param("id") Long id);
}
