package com.example.eco.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDesastreRequestDto {

    @NotBlank(message = "O campo título do alerta de desastre não pode estar vazio.")
    @Size(max = 255, message = "O título do alerta de desastre deve ter no máximo 255 caracteres.")
    private String titulo;

    @Size(max = 255, message = "A descrição do alerta de desastre deve ter no máximo 255 caracteres.")
    private String descricao;

    @NotBlank(message = "O campo nível  do alerta de desastre não pode estar vazio.")
    @Size(max = 45, message = "O nível do alerta de desastre deve ter no máximo 45 caracteres.")
    private String nivel;

    @NotBlank(message = "O campo descrição do alerta de desastre não pode estar vazio.")
    @Size(max = 45, message = "A categoria do alerta de desastre deve ter no máximo 45 caracteres.")
    private String categoria;

    // Removido, pois a data não deve ser passada pelo cliente
    // sendo geradada automaticamente pelo backend
    // @NotBlank(message = "O campo data do alerta de desastre não pode estar vazio.")
    // @PastOrPresent(message = "O campo data do alerta de desastre deve ser uma data no presente ou passado.")
    // private LocalDateTime data;
}
