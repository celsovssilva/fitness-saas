package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.Evolucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvolucaoRepository  extends JpaRepository<Evolucao, Long> {
}
