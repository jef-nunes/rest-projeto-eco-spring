package com.example.eco.api.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequestDto {

    @NotBlank(message = "O campo nome da pessoa não pode estar vazio.")
    @Size(max = 255, message = "O campo nome da pessoa deve ter no máximo 255 caracteres.")
    private String nome;

    @Size(max = 20, message = "O campo CPF da pessoa deve ter até 20 caracteres.")
    private String cpf;

    @Size(max = 20, message = "O campo CNPJ da pessoa deve ter até 20 caracteres.")
    private String cnpj;

    @Size(max = 20, message = "O campo RG da pessoa deve ter até 20 caracteres.")
    private String rg;

    @NotBlank(message = "O campo telefone da pessoa não pode estar vazio.")
    @Size(max = 20, message = "O telefone da pessoa deve ter até 20 caracteres.")
    private String telefone;

    @NotBlank(message = "O campo email da pessoa não pode estar vazio")
    @Size(max = 255, message = "O campo email da pessoa deve ter até 255 caracteres.")
    private String email;
}
