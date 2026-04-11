
package com.ecopark.ecoparkspring.repository;

import com.ecopark.ecoparkspring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para operaciones de acceso a datos de Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}