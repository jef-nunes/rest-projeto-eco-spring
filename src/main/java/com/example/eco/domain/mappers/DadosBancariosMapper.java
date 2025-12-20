package com.example.eco.domain.mappers;

import com.example.eco.api.dtos.request.DadosBancariosRequestDto;
import com.example.eco.api.dtos.response.DadosBancariosResponseDto;
import com.example.eco.domain.models.entitites.DadosBancarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DadosBancariosMapper {

    @Mapping(target="id",ignore = true)
    @Mapping(target="campanha",ignore = true)
    DadosBancarios toEntity(DadosBancariosRequestDto requestDto);

    @Mapping(target="campanhaId",ignore = true)
    DadosBancariosResponseDto toResponseDto(DadosBancarios entity);
}
