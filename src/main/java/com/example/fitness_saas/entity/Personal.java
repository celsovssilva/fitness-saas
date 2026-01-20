package com.example.fitness_saas.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cref;
    private String especialidade;

    @Column(nullable = false)
    private boolean ativo = true;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "personal")
    private List<Aluno> alunos;
}
