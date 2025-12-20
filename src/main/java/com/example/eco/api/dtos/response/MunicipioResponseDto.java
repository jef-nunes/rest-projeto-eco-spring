package com.example.eco.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioResponseDto {
    private Long id;
    private List<Long> alertaDesastreIdList;
    private  String nome;
    private String estado;
    private String regiao;
}
