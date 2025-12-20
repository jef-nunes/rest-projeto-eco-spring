package com.example.eco.services;

import com.example.eco.exceptions.ResourceNotFoundException;
import com.example.eco.domain.models.entitites.DadosBancarios;
import com.example.eco.repositories.DadosBancariosRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosBancariosService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(DadosBancariosService.class.getName());

    // Repositórios JPA
    private final DadosBancariosRepository dadosBancariosRepository;

    //          [ CRUD ]

    // Criar
    public DadosBancarios create(DadosBancarios entity){
        logger.info("Criando um dadosBancarios.");
        return dadosBancariosRepository.save(entity);
    }

    // Listar
    public List<DadosBancarios> findAll(){

        logger.info("Listando todas os dadosBancarioss.");
        return dadosBancariosRepository.findAll();
    }

    // Encontrar
    public DadosBancarios find(Long id){
        logger.info("Encontrando um dadosBancarios.");
        return dadosBancariosRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    // Atualizar
    public DadosBancarios update(Long id, DadosBancarios entity){
        logger.info("Atualizar um dadosBancarios.");
        DadosBancarios realEntity = dadosBancariosRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        realEntity.setBanco(entity.getBanco());
        realEntity.setAgencia(entity.getAgencia());
        realEntity.setConta(entity.getConta());
        realEntity.setChavePix(entity.getChavePix());
        return dadosBancariosRepository.save(realEntity);
    }

    // Remover
    public void delete(Long id){
        logger.info("Remover um dadosBancarios.");
        dadosBancariosRepository.deleteById(id);
    }

}
