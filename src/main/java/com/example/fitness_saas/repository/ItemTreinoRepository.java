package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.ItemTreino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTreinoRepository extends JpaRepository<ItemTreino,Long> {
}
