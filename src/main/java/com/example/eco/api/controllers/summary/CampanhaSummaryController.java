package com.example.eco.api.controllers.summary;

import com.example.eco.api.controllers.util.ResponseBuilder;
import com.example.eco.api.dtos.response.CampanhaSummaryResponseDto;
import com.example.eco.domain.models.ResponseModel;
import com.example.eco.domain.models.entitites.Campanha;
import com.example.eco.services.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


// Controller para resumir campanhas + seus dados bancários relacionados
@RestController
@RequestMapping("/publico/resumo/campanhas")
@RequiredArgsConstructor
public class CampanhaSummaryController {

    // Service
    private final CampanhaService campanhaService;

    // GET - Resumir todas as campanhas, retornando uma lista
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> summarizeAll() {
        // Listar os registros de campanha
        List<Campanha> entityList = campanhaService.findAll();
        // Criar a lista de retorno
        List<CampanhaSummaryResponseDto> summaryResponseDtoList = new ArrayList<>();
        // Criar cada CampanhaSummaryResponseDto e inserir na lista
        for (Campanha entity : entityList){
            CampanhaSummaryResponseDto summaryResponseDto = new CampanhaSummaryResponseDto();
            // Preencher os dados referente a própria entidade de campanha
            summaryResponseDto.setCampanhaId(entity.getId());
            summaryResponseDto.setTitulo(entity.getTitulo());
            summaryResponseDto.setDescricao(entity.getDescricao());
            summaryResponseDto.setSite(entity.getSite());
            // Preencher dados referente ao dados bancários relacionado
            if(entity.getDadosBancarios()!=null){
                summaryResponseDto.setBanco(entity.getDadosBancarios().getBanco());
                summaryResponseDto.setAgencia(entity.getDadosBancarios().getAgencia());
                summaryResponseDto.setConta(entity.getDadosBancarios().getConta());
                summaryResponseDto.setChavePix(entity.getDadosBancarios().getChavePix());
            }
            // Inserir na lista de retorno
            summaryResponseDtoList.add(summaryResponseDto);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(summaryResponseDtoList));
    }
}
