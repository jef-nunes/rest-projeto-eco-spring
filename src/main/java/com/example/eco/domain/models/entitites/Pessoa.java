package com.example.eco.domain.models.entitites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Campanha> campanhas;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = true, length = 20)
    private String cpf;

    @Column(nullable = true, length = 20)
    private String cnpj;

    @Column(nullable = true, length = 20)
    private String rg;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 255)
    private String email;
}
