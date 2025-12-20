package com.example.eco.services;

import com.example.eco.exceptions.ResourceNotFoundException;
import com.example.eco.domain.models.entitites.Pessoa;
import com.example.eco.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(PessoaService.class.getName());

    // Repositório JPA
    private final PessoaRepository pessoaRepository;


    //          [ CRUD ]

    // Criar
    public Pessoa create(Pessoa entity){
        logger.info("Criando uma pessoa.");
        return pessoaRepository.save(entity);
    }

    // Listar
    public List<Pessoa> findAll(){

        logger.info("Listando todas as pessoas.");
        return pessoaRepository.findAll();
    }

    // Encontrar
    public Pessoa find(Long id){
        logger.info("Encontrando uma pessoa.");
        return pessoaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    // Atualizar
    public Pessoa update(Long id, Pessoa entity){
        logger.info("Atualizar uma pessoa.");
        Pessoa realEntity = pessoaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        realEntity.setNome(entity.getNome());
        realEntity.setCpf(entity.getCpf());
        realEntity.setCnpj(entity.getCnpj());
        realEntity.setRg(entity.getRg());
        realEntity.setTelefone(entity.getTelefone());
        realEntity.setEmail(entity.getEmail());
        return pessoaRepository.save(realEntity);
    }

    // Remover
    public void delete(Long id){
        logger.info("Remover uma pessoa.");
        pessoaRepository.deleteById(id);
    }
}