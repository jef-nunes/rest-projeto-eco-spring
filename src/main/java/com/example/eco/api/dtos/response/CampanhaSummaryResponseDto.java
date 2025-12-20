package com.example.eco.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaSummaryResponseDto {
    private Long campanhaId;
    private String titulo;
    private String descricao;
    private String site;
    private String banco;
    private String agencia;
    private String conta;
    private String chavePix;
}
