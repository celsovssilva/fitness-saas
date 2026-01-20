package com.example.fitness_saas.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Data
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cref;
    private String especialidade;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "personal")
    private List<Aluno> alunos;
}
