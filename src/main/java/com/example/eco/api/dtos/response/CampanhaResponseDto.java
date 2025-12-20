package com.example.eco.api.dtos.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaResponseDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String site;
    private Long pessoaId;
    private Long dadosBancariosId;
}
