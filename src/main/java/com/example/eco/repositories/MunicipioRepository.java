package com.example.eco.repositories;

import com.example.eco.domain.models.entitites.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}
