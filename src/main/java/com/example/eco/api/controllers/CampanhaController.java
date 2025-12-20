package com.example.eco.api.controllers;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.request.CampanhaRequestDto;
import com.example.eco.api.dtos.response.CampanhaResponseDto;
import com.example.eco.domain.mappers.CampanhaMapper;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.Campanha;
import com.example.eco.services.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;
    private final CampanhaMapper campanhaMapper;

    // POST - Criar campanha
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody CampanhaRequestDto requestDTO) {
        Campanha savedEntity = campanhaService.create(campanhaMapper.toEntity(requestDTO));
        CampanhaResponseDto responseDto = campanhaMapper.toResponseDto(savedEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // GET - Todas as campanhas
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll() {
        List<Campanha> entityList = campanhaService.findAll();
        List<CampanhaResponseDto> responseDtoList = new ArrayList<>();
        for (Campanha entity : entityList) {
            CampanhaResponseDto responseDto = campanhaMapper.toResponseDto(entity);
            if(entity.getDadosBancarios()!=null) {
                responseDto.setDadosBancariosId(entity.getDadosBancarios().getId());
            }
            if(entity.getPessoa()!=null) {
                responseDto.setPessoaId(entity.getPessoa().getId());
            }
            responseDtoList.add(responseDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Encontrar campanha por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> find(@PathVariable Long id) {
        Campanha entity = campanhaService.find(id);
        CampanhaResponseDto responseDto = campanhaMapper.toResponseDto(entity);
        if(entity.getDadosBancarios()!=null) {
            responseDto.setDadosBancariosId(entity.getDadosBancarios().getId());
        }
        if(entity.getPessoa()!=null) {
            responseDto.setPessoaId(entity.getPessoa().getId());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // PUT - Atualizar campanha
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @Valid @RequestBody CampanhaRequestDto requestDTO) {
        Campanha savedEntity = campanhaService.update(id, campanhaMapper.toEntity(requestDTO));
        CampanhaResponseDto responseDto = campanhaMapper.toResponseDto(savedEntity);
        if(savedEntity.getDadosBancarios()!=null) {
            responseDto.setDadosBancariosId(savedEntity.getDadosBancarios().getId());
        }
        if(savedEntity.getPessoa()!=null) {
            responseDto.setPessoaId(savedEntity.getPessoa().getId());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // DELETE - Remover campanha
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        campanhaService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

    /*
            Relacionamentos
     */

    // POST - Criar relacionamento entre campanha e pessoa
    @PostMapping(value="/{campanhaId}/relacionamentos/pessoas/{pessoaId}")
    public ResponseEntity<ResponseModel> addPessoa(@PathVariable Long campanhaId, @PathVariable Long pessoaId) {
        campanhaService.associatePessoa(campanhaId,pessoaId);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

    // DELETE - Remover relacionamento entre campanha e pessoa
    @DeleteMapping(value="/{campanhaId}/relacionamentos/pessoas/{pessoaId}")
    public ResponseEntity<ResponseModel> removePessoa(@PathVariable Long campanhaId, @PathVariable Long pessoaId) {
        campanhaService.disassociatePessoa(campanhaId,pessoaId);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

    // POST - Criar relacionamento entre campanha e dados bancários
    @PostMapping(value="/{campanhaId}/relacionamentos/dados-bancarios/{dadosBancariosId}")
    public ResponseEntity<ResponseModel> addDadosBancarios(@PathVariable Long campanhaId, @PathVariable Long dadosBancariosId) {
        campanhaService.associateDadosBancarios(campanhaId,dadosBancariosId);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

    // DELETE - Remover relacionamento entre campanha e dados bancários
    @DeleteMapping(value="/{campanhaId}/relacionamentos/dados-bancarios/{dadosBancariosId}")
    public ResponseEntity<ResponseModel> removeDadosBancarios(@PathVariable Long campanhaId, @PathVariable Long dadosBancariosId) {
        campanhaService.disassociateDadosBancarios(campanhaId,dadosBancariosId);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }
}
