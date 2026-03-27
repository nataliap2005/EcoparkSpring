package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Usuario;
import com.ecopark.ecoparkspring.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar usuarios.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}