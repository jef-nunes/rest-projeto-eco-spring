package com.example.eco.domain.models.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "alerta_desastre")
public class AlertaDesastre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String descricao;

    @Column(nullable = false, length = 45)
    private String nivel;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

}
