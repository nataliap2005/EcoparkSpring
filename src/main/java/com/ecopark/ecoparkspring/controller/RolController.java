package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Rol;
import com.ecopark.ecoparkspring.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para consultar los roles disponibles del sistema.
 * Los roles no se eliminan desde la API porque pueden estar asociados a usuarios.
 */
@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:5173")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}