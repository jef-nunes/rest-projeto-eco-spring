package com.example.eco.repositories;

import com.example.eco.domain.models.entitites.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
