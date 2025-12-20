package com.example.eco.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDesastreResponseDto {
    private Long id;
    private Long municipioId;
    private String titulo;
    private String descricao;
    private String nivel;
    private String categoria;
    private LocalDateTime data;
}
