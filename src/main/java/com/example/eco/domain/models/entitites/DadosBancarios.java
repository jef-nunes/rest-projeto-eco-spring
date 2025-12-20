package com.example.eco.domain.models.entitites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "dados_bancarios")
public class DadosBancarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento
    @OneToOne(mappedBy = "dadosBancarios")
    private Campanha campanha;

    @Column(nullable = false, length = 100)
    private String banco;

    @Column(nullable = true, length = 45)
    private String agencia;

    @Column(nullable = true, length = 45)
    private String conta;

    @Column(nullable = true, length = 255)
    private String chavePix;

}
