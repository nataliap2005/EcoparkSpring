package com.ecopark.ecoparkspring.repository;

import com.ecopark.ecoparkspring.model.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Integer> {
}