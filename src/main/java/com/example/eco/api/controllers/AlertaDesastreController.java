package com.example.eco.api.controllers;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.request.AlertaDesastreRequestDto;
import com.example.eco.api.dtos.response.AlertaDesastreResponseDto;
import com.example.eco.domain.mappers.AlertaDesastreMapper;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.AlertaDesastre;
import com.example.eco.services.AlertaDesastreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/alertas-desastres")
@RequiredArgsConstructor
public class AlertaDesastreController {

    private final AlertaDesastreService alertaDesastreService;
    private final AlertaDesastreMapper alertaDesastreMapper;

    // POST - Criar alertaDesastre
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody AlertaDesastreRequestDto requestDTO) {
        AlertaDesastre tempEntity = alertaDesastreMapper.toEntity(requestDTO);
        // A data (LocalDateTime) é adicionada pelo backend
        tempEntity.setData(LocalDateTime.now());
        AlertaDesastre savedEntity = alertaDesastreService.create(tempEntity);
        AlertaDesastreResponseDto responseDto = alertaDesastreMapper.toResponseDto(savedEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // GET - Todas as alertaDesastres
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll() {
        List<AlertaDesastre> entityList = alertaDesastreService.findAll();
        List<AlertaDesastreResponseDto> responseDtoList = new ArrayList<>();
        if(!entityList.isEmpty()) {
            for (AlertaDesastre entity : entityList) {
                AlertaDesastreResponseDto responseDto = alertaDesastreMapper.toResponseDto(entity);
                if(entity.getMunicipio()!=null) {
                    responseDto.setMunicipioId(entity.getMunicipio().getId());
                }
                responseDtoList.add(responseDto);
            }
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Encontrar alertaDesastre por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> find(@PathVariable Long id) {
        AlertaDesastre entity = alertaDesastreService.find(id);
        AlertaDesastreResponseDto responseDto = alertaDesastreMapper.toResponseDto(entity);
        if(entity.getMunicipio()!=null) {
            responseDto.setMunicipioId(entity.getMunicipio().getId());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // PUT - Atualizar alertaDesastre
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @Valid @RequestBody AlertaDesastreRequestDto requestDTO) {
        AlertaDesastre savedEntity = alertaDesastreService.update(id, alertaDesastreMapper.toEntity(requestDTO));
        AlertaDesastreResponseDto responseDto = alertaDesastreMapper.toResponseDto(savedEntity);
        if(savedEntity.getMunicipio()!=null) {
            responseDto.setMunicipioId(savedEntity.getMunicipio().getId());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // DELETE - Remover alertaDesastre
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        alertaDesastreService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

        /*
            Relacionamentos
     */

    // POST - Criar relacionamento entre alerta de desastre e municipio
    @PostMapping(value="/{alertaDesastreId}/relacionamentos/municipios/{municipioId}")
    public ResponseEntity<ResponseModel> addMunicipio(@PathVariable Long alertaDesastreId, @PathVariable Long municipioId) {
        alertaDesastreService.associateMunicipio(alertaDesastreId,municipioId);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

    // DELETE - Remover relacionamento entre alerta de desastre e municipio
    @DeleteMapping(value="/{alertaDesastreId}/relacionamentos/municipios/{municipioId}")
    public ResponseEntity<ResponseModel> removeMunicipio(@PathVariable Long alertaDesastreId, @PathVariable Long municipioId) {
        alertaDesastreService.disassociateMunicipio(alertaDesastreId,municipioId);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }
}