package com.example.fitness_saas.entity;


import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cref;
    private String especialidade;

    @Column(nullable = false)
    private boolean ativo = true;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "personal")
    private List<Aluno> alunos;
}
