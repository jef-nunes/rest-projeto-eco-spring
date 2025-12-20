package com.example.eco.api.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosBancariosRequestDto {

    @NotBlank(message = "O campo banco dos dados bancários não pode estar vazio.")
    @Size(min = 1, max = 100, message = "O campo banco dos dados bancários  deve ter entre 1 e 100 caracteres.")
    private String banco;

    @Size(max = 45, message = "O campo agência dos dados bancários deve ter entre 1 e 45 caracteres.")
    private String agencia;

    @Size(max = 45, message = "O campo conta dos dados bancários deve ter entre 1 e 45 caracteres.")
    private String conta;

    @Size(max = 255, message = "O campo chave pix dos dados bancários  deve ter entre 1 e 255 caracteres.")
    private String chavePix;

}
