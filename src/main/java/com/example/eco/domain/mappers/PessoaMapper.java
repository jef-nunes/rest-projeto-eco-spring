package com.example.eco.domain.mappers;

import com.example.eco.api.dtos.request.PessoaRequestDto;
import com.example.eco.api.dtos.response.PessoaResponseDto;
import com.example.eco.domain.models.entitites.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    @Mapping(target="id",ignore = true)
    @Mapping(target="campanhas",ignore = true)
    Pessoa toEntity(PessoaRequestDto requestDto);

    @Mapping(target="campanhaIdList",ignore = true)
    PessoaResponseDto toResponseDto(Pessoa entity);
}
