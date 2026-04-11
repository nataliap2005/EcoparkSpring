//
//package com.ecopark.ecoparkspring.service;
//
//import com.ecopark.ecoparkspring.model.Usuario;
//import com.ecopark.ecoparkspring.repository.UsuarioRepository;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Servicio que contiene la lógica de negocio para Usuario.
// */
//@Service
//public class UsuarioService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    public Usuario guardarUsuario(Usuario usuario) {
//        return usuarioRepository.save(usuario);
//    }
//
//    public List<Usuario> listarUsuarios() {
//        return usuarioRepository.findAll();
//    }
//
//    public Optional<Usuario> buscarPorId(int id) {
//        return usuarioRepository.findById(id);
//    }
//
//    public Usuario actualizarUsuario(int id, Usuario usuarioActualizado) {
//        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
//
//        if (usuarioExistente.isPresent()) {
//            Usuario usuario = usuarioExistente.get();
//            usuario.setNombre(usuarioActualizado.getNombre());
//            usuario.setEmail(usuarioActualizado.getEmail());
//            usuario.setContrasena(usuarioActualizado.getContrasena());
//            usuario.setCelular(usuarioActualizado.getCelular());
//            usuario.setDiscapacidad(usuarioActualizado.isDiscapacidad());
//            usuario.setMetodoPago(usuarioActualizado.getMetodoPago());
//
//            return usuarioRepository.save(usuario);
//        } else {
//            return null;
//        }
//    }
//
//    public boolean eliminarUsuario(int id) {
//        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
//
//        if (usuarioExistente.isPresent()) {
//            usuarioRepository.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
//    }
//}

package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Usuario;
import com.ecopark.ecoparkspring.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
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