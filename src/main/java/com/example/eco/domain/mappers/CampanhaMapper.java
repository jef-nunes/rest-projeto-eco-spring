package com.example.eco.domain.mappers;

import com.example.eco.api.dtos.request.CampanhaRequestDto;
import com.example.eco.api.dtos.response.CampanhaResponseDto;
import com.example.eco.domain.models.entitites.Campanha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampanhaMapper {

    @Mapping(target="id",ignore = true)
    @Mapping(target="dadosBancarios",ignore = true)
    @Mapping(target="pessoa",ignore = true)
    Campanha toEntity(CampanhaRequestDto requestDto);

    @Mapping(target="dadosBancariosId",ignore = true)
    @Mapping(target="pessoaId",ignore = true)
    CampanhaResponseDto toResponseDto(Campanha entity);
}
