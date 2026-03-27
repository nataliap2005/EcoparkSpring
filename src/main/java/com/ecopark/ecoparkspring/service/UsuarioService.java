package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Usuario;
import com.ecopark.ecoparkspring.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que contiene la lógica de negocio para Usuario.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}