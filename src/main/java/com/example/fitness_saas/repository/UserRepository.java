package com.example.fitness_saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fitness_saas.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
