package com.example.eco.api.dtos.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosBancariosResponseDto {
    private Long id;
    private String banco;
    private String agencia;
    private String conta;
    private String chavePix;
    private Long campanhaId;
}
