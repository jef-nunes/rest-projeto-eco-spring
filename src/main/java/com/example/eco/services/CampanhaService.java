package com.example.eco.services;

import com.example.eco.exceptions.ResourceNotFoundException;
import com.example.eco.domain.models.entitites.Campanha;
import com.example.eco.domain.models.entitites.DadosBancarios;
import com.example.eco.domain.models.entitites.Pessoa;
import com.example.eco.repositories.CampanhaRepository;
import com.example.eco.repositories.DadosBancariosRepository;
import com.example.eco.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(CampanhaService.class.getName());

    // Repositórios JPA
    private final CampanhaRepository campanhaRepository;
    private final PessoaRepository pessoaRepository;
    private final DadosBancariosRepository dadosBancariosRepository;

    //          [ CRUD ]

    // Criar
    public Campanha create(Campanha entity){
        logger.info("Criando uma campanha.");
        return campanhaRepository.save(entity);
    }

    // Listar
    public List<Campanha> findAll(){

        logger.info("Listando todas as campanhas.");
        return campanhaRepository.findAll();
    }

    // Encontrar
    public Campanha find(Long id){
        logger.info("Encontrando uma campanha.");
        return campanhaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    // Atualizar
    public Campanha update(Long id, Campanha entity){
        logger.info("Atualizar uma campanha.");
        Campanha realEntity = campanhaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        realEntity.setTitulo(entity.getTitulo());
        realEntity.setDescricao(entity.getDescricao());
        realEntity.setSite(entity.getSite());
        return campanhaRepository.save(realEntity);
    }

    // Remover
    public void delete(Long id){
        logger.info("Remover uma campanha.");
        Campanha entity = campanhaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        entity.setPessoa(null);
        entity.setDadosBancarios(null);
        campanhaRepository.save(entity);
        campanhaRepository.deleteById(id);
    }

    //          [ Relacionamentos ]

    // Relacionar campanha e pessoa
    public void associatePessoa(Long campanhaId, Long pessoaId){
        logger.info("Relacionar campanha e pessoa.");
        Campanha campanha = campanhaRepository.findById(campanhaId).orElseThrow(ResourceNotFoundException::new);
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(ResourceNotFoundException::new);
        campanha.setPessoa(pessoa);
        pessoa.getCampanhas().add(campanha);
        campanhaRepository.save(campanha);
        pessoaRepository.save(pessoa);
    }

    // Remover relacionamento entre campanha e pessoa
    public void disassociatePessoa(Long campanhaId, Long pessoaId){
        logger.info("Remover relacionamento entre campanha e pessoa.");
        Campanha campanha = campanhaRepository.findById(campanhaId).orElseThrow(ResourceNotFoundException::new);
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(ResourceNotFoundException::new);
        campanha.setPessoa(null);
        pessoa.getCampanhas().remove(campanha);
        campanhaRepository.save(campanha);
        pessoaRepository.save(pessoa);
    }

    // Relacionar campanha e dados bancários
    public void associateDadosBancarios(Long campanhaId, Long dadosBancariosId){
        logger.info("Relacionar campanha e dados bancários.");
        Campanha campanha = campanhaRepository.findById(campanhaId).orElseThrow(ResourceNotFoundException::new);
        DadosBancarios dadosBancarios = dadosBancariosRepository.findById(dadosBancariosId).orElseThrow(ResourceNotFoundException::new);
        campanha.setDadosBancarios(dadosBancarios);
        dadosBancarios.setCampanha(campanha);
        campanhaRepository.save(campanha);
        dadosBancariosRepository.save(dadosBancarios);
    }

    // Remover relacionamento entre campanha e dados bancários
    public void disassociateDadosBancarios(Long campanhaId, Long dadosBancariosId){
        logger.info("Remover relacionamento entre campanha e dados bancários");
        Campanha campanha = campanhaRepository.findById(campanhaId).orElseThrow(ResourceNotFoundException::new);
        DadosBancarios dadosBancarios = dadosBancariosRepository.findById(dadosBancariosId).orElseThrow(ResourceNotFoundException::new);
        campanha.setDadosBancarios(null);
        dadosBancarios.setCampanha(null);
        campanhaRepository.save(campanha);
        dadosBancariosRepository.save(dadosBancarios);
    }
}
