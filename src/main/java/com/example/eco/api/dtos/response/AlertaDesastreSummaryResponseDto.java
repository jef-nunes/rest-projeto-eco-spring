package com.example.eco.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDesastreSummaryResponseDto {
    private Long alertaDesastreId;
    private String titulo;
    private String descricao;
    private String nivel;
    private String categoria;
    private LocalDateTime data;
    private String localMunicipio;
    private String localEstado;
    private String localRegiao;
}
