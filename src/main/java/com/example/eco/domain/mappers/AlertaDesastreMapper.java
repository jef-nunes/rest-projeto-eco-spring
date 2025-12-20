package com.example.eco.domain.mappers;

import com.example.eco.api.dtos.request.AlertaDesastreRequestDto;
import com.example.eco.api.dtos.response.AlertaDesastreResponseDto;
import com.example.eco.domain.models.entitites.AlertaDesastre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlertaDesastreMapper {

    @Mapping(target="id",ignore = true)
    @Mapping(target = "municipio", ignore = true)
    AlertaDesastre toEntity(AlertaDesastreRequestDto requestDto);

    @Mapping(target = "municipioId", ignore = true)
    AlertaDesastreResponseDto toResponseDto(AlertaDesastre entity);
}