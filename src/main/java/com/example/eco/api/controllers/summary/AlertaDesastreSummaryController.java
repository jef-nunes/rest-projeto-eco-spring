package com.example.eco.api.controllers.summary;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.response.AlertaDesastreResponseDto;
import com.example.eco.api.dtos.response.AlertaDesastreSummaryResponseDto;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.AlertaDesastre;
import com.example.eco.services.AlertaDesastreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Controller para resumir alertas de desastres + município relacionado
@RestController
@RequestMapping("/publico/resumo/alertas-desastres")
@RequiredArgsConstructor
public class AlertaDesastreSummaryController {

    // Service
    private final AlertaDesastreService alertaDesastreService;

    // GET - Resumir todas os alertas desastres, retornando uma lista
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> fromAll() {
        List<AlertaDesastre> entityList = alertaDesastreService.findAll();
        List<AlertaDesastreSummaryResponseDto> summaryResponseDtoList = new ArrayList<>();

        for(AlertaDesastre entity : entityList){
            AlertaDesastreSummaryResponseDto responseDto = new AlertaDesastreSummaryResponseDto();
            // Dados do proprio alerta de desastre
            responseDto.setTitulo(entity.getTitulo());
            responseDto.setDescricao(entity.getDescricao());
            responseDto.setNivel(entity.getNivel());
            responseDto.setCategoria(entity.getCategoria());
            responseDto.setData(entity.getData());
            // Dados do municipio relacionado
            if(entity.getMunicipio()!=null){
                responseDto.setLocalMunicipio(entity.getMunicipio().getNome());
                responseDto.setLocalEstado(entity.getMunicipio().getEstado());
                responseDto.setLocalRegiao(entity.getMunicipio().getRegiao());
            }
            summaryResponseDtoList.add(responseDto);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(summaryResponseDtoList));
    }
}
