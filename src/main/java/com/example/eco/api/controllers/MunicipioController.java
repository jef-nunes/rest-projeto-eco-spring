package com.example.eco.api.controllers;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.request.MunicipioRequestDto;
import com.example.eco.api.dtos.response.MunicipioResponseDto;
import com.example.eco.domain.mappers.MunicipioMapper;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.AlertaDesastre;
import com.example.eco.domain.models.entitites.Municipio;
import com.example.eco.services.MunicipioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;
    private final MunicipioMapper municipioMapper;

    // POST - Criar municipio
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody MunicipioRequestDto requestDTO) {
        Municipio savedEntity = municipioService.create(municipioMapper.toEntity(requestDTO));
        MunicipioResponseDto responseDto = municipioMapper.toResponseDto(savedEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // GET - Todas as municipios
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll() {
        List<Municipio> entityList = municipioService.findAll();
        List<MunicipioResponseDto> responseDtoList = new ArrayList<>();
        // Para cada entidade listada
        for (Municipio entity : entityList) {
            // Lógica para criar corretamente a lista de IDs de alertas de desastres relacionados
            MunicipioResponseDto responseDto = municipioMapper.toResponseDto(entity);
            List<Long> alertaDesastreIdList = new ArrayList<>();
            if(!entity.getAlertaDesastreList().isEmpty()){
            for (AlertaDesastre alertaDesastre : entity.getAlertaDesastreList()) {
                Long alertaDesastreId = alertaDesastre.getId();
                alertaDesastreIdList.add(alertaDesastreId);
            }
            }
            responseDto.setAlertaDesastreIdList(alertaDesastreIdList);
            responseDtoList.add(responseDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Encontrar municipio por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> find(@PathVariable Long id) {
        Municipio entity = municipioService.find(id);
        MunicipioResponseDto responseDto = municipioMapper.toResponseDto(entity);
        // Lógica para criar corretamente a lista de IDs de alertas de desastres relacionados
        List<Long> alertaDesastreIdList = new ArrayList<>();
        if(!entity.getAlertaDesastreList().isEmpty()) {
            for (AlertaDesastre alertaDesastre : entity.getAlertaDesastreList()) {
                Long alertaDesastreId = alertaDesastre.getId();
                alertaDesastreIdList.add(alertaDesastreId);
            }
        }
        responseDto.setAlertaDesastreIdList(alertaDesastreIdList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // PUT - Atualizar municipio
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @Valid @RequestBody MunicipioRequestDto requestDTO) {
        Municipio savedEntity = municipioService.update(id, municipioMapper.toEntity(requestDTO));
        MunicipioResponseDto responseDto = municipioMapper.toResponseDto(savedEntity);
        // Lógica para criar corretamente a lista de IDs de alertas de desastres relacionados
        List<Long> alertaDesastreIdList = new ArrayList<>();
        if(!savedEntity.getAlertaDesastreList().isEmpty()) {
            for (AlertaDesastre alertaDesastre : savedEntity.getAlertaDesastreList()) {
                Long alertaDesastreId = alertaDesastre.getId();
                alertaDesastreIdList.add(alertaDesastreId);
            }
        }
        responseDto.setAlertaDesastreIdList(alertaDesastreIdList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // DELETE - Remover municipio
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        municipioService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

}