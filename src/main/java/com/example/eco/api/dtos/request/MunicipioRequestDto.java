package com.example.eco.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioRequestDto {

    @NotBlank(message = "O campo nome do municipio não deve estar vazio.")
    @Size(max = 255, message = "O nome do município deve ter no máximo 255 caracteres.")
    private String nome;

    @NotBlank(message = "O campo estado do municipio não deve estar vazio.")
    @Size(min=2,max=2, message = "O estado do município deve ter exatamente 2 caracteres.")
    private String estado;


    @NotBlank(message = "O campo regiao do municipio não deve estar vazio.")
    @Size(min = 1, max = 2, message = "A regiao do município deve ter entre 1 a 2 caracteres.")
    private String regiao;
}
