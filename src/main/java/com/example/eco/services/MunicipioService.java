package com.example.eco.services;

import com.example.eco.exceptions.ResourceNotFoundException;
import com.example.eco.domain.models.entitites.Municipio;
import com.example.eco.repositories.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(MunicipioService.class.getName());

    // Repositório JPA
    private final MunicipioRepository municipioRepository;


    //          [ CRUD ]

    // Criar
    public Municipio create(Municipio entity){
        logger.info("Criando um municipio.");
        return municipioRepository.save(entity);
    }

    // Listar
    public List<Municipio> findAll(){

        logger.info("Listando todos os municipios.");
        return municipioRepository.findAll();
    }

    // Encontrar
    public Municipio find(Long id){
        logger.info("Encontrando um municipio.");
        return municipioRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    // Atualizar
    public Municipio update(Long id, Municipio entity){
        logger.info("Atualizar um municipio.");
        Municipio realEntity = municipioRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        realEntity.setNome(entity.getNome());
        realEntity.setEstado(entity.getEstado());
        realEntity.setRegiao(entity.getRegiao());
        return municipioRepository.save(realEntity);
    }

    // Remover
    public void delete(Long id){
        logger.info("Remover um municipio.");
        municipioRepository.deleteById(id);
    }
}