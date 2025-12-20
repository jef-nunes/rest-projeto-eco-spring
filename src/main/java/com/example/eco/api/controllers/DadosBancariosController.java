package com.example.eco.api.controllers;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.request.DadosBancariosRequestDto;
import com.example.eco.api.dtos.response.DadosBancariosResponseDto;
import com.example.eco.domain.mappers.DadosBancariosMapper;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.DadosBancarios;
import com.example.eco.services.DadosBancariosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/dados-bancarios")
@RequiredArgsConstructor
public class DadosBancariosController {

    private final DadosBancariosService dadosBancariosService;
    private final DadosBancariosMapper dadosBancariosMapper;

    // POST - Criar dadosBancarios
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody DadosBancariosRequestDto requestDTO) {
        DadosBancarios savedEntity = dadosBancariosService.create(dadosBancariosMapper.toEntity(requestDTO));
        DadosBancariosResponseDto responseDto = dadosBancariosMapper.toResponseDto(savedEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // GET - Todas as dadosBancarioss
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll() {
        List<DadosBancarios> entityList = dadosBancariosService.findAll();
        List<DadosBancariosResponseDto> responseDtoList = new ArrayList<>();
        for (DadosBancarios entity : entityList) {
            DadosBancariosResponseDto responseDto = dadosBancariosMapper.toResponseDto(entity);
            if(entity.getCampanha()!=null) {
                responseDto.setCampanhaId(entity.getCampanha().getId());
            }
            responseDtoList.add(responseDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Encontrar dadosBancarios por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> find(@PathVariable Long id) {
        DadosBancarios entity = dadosBancariosService.find(id);
        DadosBancariosResponseDto responseDto = dadosBancariosMapper.toResponseDto(entity);
        if(entity.getCampanha()!=null) {
            responseDto.setCampanhaId(entity.getCampanha().getId());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // PUT - Atualizar dadosBancarios
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @Valid @RequestBody DadosBancariosRequestDto requestDTO) {
        DadosBancarios savedEntity = dadosBancariosService.update(id, dadosBancariosMapper.toEntity(requestDTO));
        DadosBancariosResponseDto responseDto = dadosBancariosMapper.toResponseDto(savedEntity);
        if(savedEntity.getCampanha()!=null) {
            responseDto.setCampanhaId(savedEntity.getCampanha().getId());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // DELETE - Remover dadosBancarios
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        dadosBancariosService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

}
