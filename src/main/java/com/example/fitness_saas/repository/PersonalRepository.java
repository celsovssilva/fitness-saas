package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository  extends JpaRepository<Personal, Long> {
}
