package com.example.eco.api.controllers;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.request.PessoaRequestDto;
import com.example.eco.api.dtos.response.PessoaResponseDto;
import com.example.eco.domain.mappers.PessoaMapper;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.Campanha;
import com.example.eco.domain.models.entitites.Pessoa;
import com.example.eco.services.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    // POST - Criar pessoa
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody PessoaRequestDto requestDTO) {
        Pessoa savedEntity = pessoaService.create(pessoaMapper.toEntity(requestDTO));
        PessoaResponseDto responseDto = pessoaMapper.toResponseDto(savedEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // GET - Todas as pessoas
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll() {
        List<Pessoa> entityList = pessoaService.findAll();
        List<PessoaResponseDto> responseDtoList = new ArrayList<>();
        for (Pessoa entity : entityList) {
            PessoaResponseDto responseDto = pessoaMapper.toResponseDto(entity);
            List<Long> campanhaIdList = new ArrayList<>();
            if(!entity.getCampanhas().isEmpty()) {
                for (Campanha campanha : entity.getCampanhas()) {
                    campanhaIdList.add(campanha.getId());
                }
            }
            responseDto.setCampanhaIdList(campanhaIdList);
            responseDtoList.add(responseDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Encontrar pessoa por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> find(@PathVariable Long id) {
        Pessoa entity = pessoaService.find(id);
        PessoaResponseDto responseDto = pessoaMapper.toResponseDto(entity);
        List<Long> campanhaIdList = new ArrayList<>();
        if(!entity.getCampanhas().isEmpty()) {
            for (Campanha campanha : entity.getCampanhas()) {
                campanhaIdList.add(campanha.getId());
            }
        }
        responseDto.setCampanhaIdList(campanhaIdList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // PUT - Atualizar pessoa
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @Valid @RequestBody PessoaRequestDto requestDTO) {
        Pessoa savedEntity = pessoaService.update(id, pessoaMapper.toEntity(requestDTO));
        PessoaResponseDto responseDto = pessoaMapper.toResponseDto(savedEntity);
        List<Long> campanhaIdList = new ArrayList<>();
        if(!savedEntity.getCampanhas().isEmpty()) {
            for (Campanha campanha : savedEntity.getCampanhas()) {
                campanhaIdList.add(campanha.getId());
            }
        }
        responseDto.setCampanhaIdList(campanhaIdList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // DELETE - Remover pessoa
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

}
