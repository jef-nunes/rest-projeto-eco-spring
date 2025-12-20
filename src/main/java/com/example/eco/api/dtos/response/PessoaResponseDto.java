package com.example.eco.api.dtos.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponseDto {
    private Long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String rg;
    private String telefone;
    private String email;
    private List<Long> campanhaIdList;
}
