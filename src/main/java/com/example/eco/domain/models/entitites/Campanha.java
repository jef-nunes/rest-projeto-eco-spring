package com.example.eco.domain.models.entitites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "campanha")
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dados_bancarios_id", referencedColumnName = "id")
    private DadosBancarios dadosBancarios;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(length = 255)
    private String site;
}
