package com.example.eco.domain.models.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 2)
    private String regiao;

    @OneToMany(mappedBy = "municipio", fetch = FetchType.EAGER)
    private List<AlertaDesastre> alertaDesastreList;

}
