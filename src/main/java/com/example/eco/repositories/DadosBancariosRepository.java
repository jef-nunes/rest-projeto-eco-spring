package com.example.eco.repositories;

import com.example.eco.domain.models.entitites.DadosBancarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long> {
}
