package com.ecopark.ecoparkspring.repository;

import com.ecopark.ecoparkspring.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}