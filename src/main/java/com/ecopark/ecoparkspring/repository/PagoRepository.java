package com.ecopark.ecoparkspring.repository;

import com.ecopark.ecoparkspring.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
}