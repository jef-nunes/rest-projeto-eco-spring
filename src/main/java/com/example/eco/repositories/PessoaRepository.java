package com.example.eco.repositories;

import com.example.eco.domain.models.entitites.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
