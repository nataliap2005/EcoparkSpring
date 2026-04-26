package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Rol;
import com.ecopark.ecoparkspring.model.Usuario;
import com.ecopark.ecoparkspring.repository.RolRepository;
import com.ecopark.ecoparkspring.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        Rol rolCliente = rolRepository.findByNombre("cliente")
                .orElseThrow(() -> new RuntimeException("No existe el rol cliente en la base de datos"));

        usuario.setRol(rolCliente);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(int id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();

            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setCelular(usuarioActualizado.getCelular());
            usuario.setDiscapacidad(usuarioActualizado.isDiscapacidad());
            usuario.setMetodoPago(usuarioActualizado.getMetodoPago());

            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    public boolean eliminarUsuario(int id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}