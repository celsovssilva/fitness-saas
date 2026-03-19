package com.example.fitness_saas.repository;

import com.example.fitness_saas.entity.PassWordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassWordTokenRepository  extends JpaRepository<PassWordReset, Long> {
    PassWordReset findByToken(String token);
}
