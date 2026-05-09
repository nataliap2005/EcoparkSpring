package com.ecopark.ecoparkspring.repository;

import com.ecopark.ecoparkspring.model.IngresoSalida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoSalidaRepository extends JpaRepository<IngresoSalida, Integer> {
}