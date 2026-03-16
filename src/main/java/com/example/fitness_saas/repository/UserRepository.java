package com.example.fitness_saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fitness_saas.entity.User;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
