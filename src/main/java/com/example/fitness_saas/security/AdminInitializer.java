package com.example.fitness_saas.security;

import com.example.fitness_saas.Enum.UserRole;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

//cria o admin do sistema
@Configuration
public class AdminInitializer {


    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@admin.com.br") == null) {
                User admin = new User();
                admin.setName("admin");
                admin.setEmail("admin@admin.com.br");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole(UserRole.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin has been created");
            }
        };
    }
}
