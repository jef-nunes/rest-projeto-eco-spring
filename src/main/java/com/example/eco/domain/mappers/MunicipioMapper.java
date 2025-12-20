package com.example.eco.domain.mappers;

import com.example.eco.api.dtos.request.MunicipioRequestDto;
import com.example.eco.api.dtos.response.MunicipioResponseDto;
import com.example.eco.domain.models.entitites.Municipio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    @Mapping(target="id",ignore = true)
    @Mapping(target = "alertaDesastreList", ignore = true)
    Municipio toEntity(MunicipioRequestDto requestDto);

    @Mapping(target = "alertaDesastreIdList", ignore = true)
    MunicipioResponseDto toResponseDto(Municipio entity);
}
