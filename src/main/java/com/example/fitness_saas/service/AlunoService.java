package com.example.fitness_saas.service;

import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.repository.AlunoRepository;
import com.example.fitness_saas.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final UserRepository userRepository;
    private final AlunoRepository alunoRepository;

    @Transactional
    User trainer = userRepository.findById(request.trainerId())
            .orElseThrow(() -> new RuntimeException("Personal não encontrado"));

    User nutri = userRepository.findById(request.nutritionistId())
            .orElseThrow(() -> new RuntimeException("Nutricionista não encontrado"));


    User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(UserRole.STUDENT);
        userRepository.save(user);


    Aluno student = new Student();
        student.setUser(user);
        student.setTrainer(trainer);
        student.setNutritionist(nutri);
        student.setObjective(request.objective());
        AlunoRepository.save(student);
}

}
