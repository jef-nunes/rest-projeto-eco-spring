package com.example.eco.services;

import com.example.eco.domain.models.entitites.*;
import com.example.eco.exceptions.ResourceNotFoundException;
import com.example.eco.repositories.AlertaDesastreRepository;
import com.example.eco.repositories.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaDesastreService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(AlertaDesastreService.class.getName());

    // Repositórios JPA
    private final AlertaDesastreRepository alertaDesastreRepository;
    private final MunicipioRepository municipioRepository;


    //          [ CRUD ]

    // Criar
    public AlertaDesastre create(AlertaDesastre entity){
        logger.info("Criando um alertaDesastre.");
        return alertaDesastreRepository.save(entity);
    }

    // Listar
    public List<AlertaDesastre> findAll(){

        logger.info("Listando todos os alertaDesastres.");
        return alertaDesastreRepository.findAll();
    }

    // Encontrar
    public AlertaDesastre find(Long id){
        logger.info("Encontrando um alertaDesastre.");
        return alertaDesastreRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    // Atualizar
    public AlertaDesastre update(Long id, AlertaDesastre entity){
        logger.info("Atualizar um alertaDesastre.");
        AlertaDesastre realEntity = alertaDesastreRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        realEntity.setTitulo(entity.getTitulo());
        realEntity.setDescricao(entity.getDescricao());
        realEntity.setNivel(entity.getNivel());
        realEntity.setCategoria(entity.getCategoria());
        return alertaDesastreRepository.save(realEntity);
    }

    // Remover
    public void delete(Long id){
        logger.info("Remover um alertaDesastre.");
        AlertaDesastre entity = alertaDesastreRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        entity.setMunicipio(null);
        alertaDesastreRepository.save(entity);
        alertaDesastreRepository.deleteById(id);
    }

    //          [ Relacionamentos ]

    // Relacionar alerta de desastre e municipio
    public void associateMunicipio(Long alertaDesastreId, Long municipioId){
        logger.info("Relacionar alerta de desastre e municipio.");
        AlertaDesastre alertaDesastre = alertaDesastreRepository.findById(alertaDesastreId).orElseThrow(ResourceNotFoundException::new);
        Municipio municipio = municipioRepository.findById(municipioId).orElseThrow(ResourceNotFoundException::new);
        alertaDesastre.setMunicipio(municipio);
        municipio.getAlertaDesastreList().add(alertaDesastre);
        alertaDesastreRepository.save(alertaDesastre);
        municipioRepository.save(municipio);
    }

    // Remover relacionamento entre alerta de desastre e municipio
    public void disassociateMunicipio(Long alertaDesastreId, Long municipioId){
        logger.info("Remover relacionamento entre alerta de desastre e municipio.");
        AlertaDesastre alertaDesastre = alertaDesastreRepository.findById(alertaDesastreId).orElseThrow(ResourceNotFoundException::new);
        Municipio municipio = municipioRepository.findById(municipioId).orElseThrow(ResourceNotFoundException::new);
        municipio.getAlertaDesastreList().remove(alertaDesastre);
        alertaDesastre.setMunicipio(null);
        alertaDesastreRepository.save(alertaDesastre);
        municipioRepository.save(municipio);
    }

}