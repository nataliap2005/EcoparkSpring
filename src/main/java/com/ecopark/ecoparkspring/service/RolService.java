package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Rol;
import com.ecopark.ecoparkspring.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar la lógica relacionada con los roles del sistema.
 */
@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> buscarPorId(int id) {
        return rolRepository.findById(id);
    }

    public Rol actualizarRol(int id, Rol rolActualizado) {
        Optional<Rol> rolExistente = rolRepository.findById(id);

        if (rolExistente.isPresent()) {
            Rol rol = rolExistente.get();
            rol.setNombre(rolActualizado.getNombre());
            return rolRepository.save(rol);
        } else {
            return null;
        }
    }

    public boolean eliminarRol(int id) {
        Optional<Rol> rolExistente = rolRepository.findById(id);

        if (rolExistente.isPresent()) {
            rolRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}