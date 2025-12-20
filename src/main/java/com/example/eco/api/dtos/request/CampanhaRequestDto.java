package com.example.eco.api.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaRequestDto {

    @NotBlank(message = "O campo título da campanha não deve estar vazio.")
    @Size(min = 10, max = 255, message = "O campo título da campanha deve ter entre 10 e 255 caracteres.")
    private String titulo;

    @NotBlank(message = "O campo descrição da campanha não deve estar vazio.")
    @Size(min = 10, max = 255, message = "O campo descrição da campanha deve ter entre 10 e 255 caracteres.")
    private String descricao;

    @Size(max=255, message="O campo site da campanha deve conter até 255 caracteres.")
    private String site;
}
