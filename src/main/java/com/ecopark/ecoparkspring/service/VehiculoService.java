package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Usuario;
import com.ecopark.ecoparkspring.model.Vehiculo;
import com.ecopark.ecoparkspring.repository.UsuarioRepository;
import com.ecopark.ecoparkspring.repository.VehiculoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {

        if (vehiculo.getTipo() == null) {
            throw new RuntimeException("El tipo de vehículo es obligatorio");
        }

        if (vehiculo.getUsuario() == null) {
            throw new RuntimeException("Debe asociar un usuario al vehículo");
        }

        int idUsuario = vehiculo.getUsuario().getIdUsuario();

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException(
                        "No se puede crear el vehículo. El usuario con ID "
                        + idUsuario + " no existe"
                ));

        vehiculo.setUsuario(usuario);

        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> buscarPorId(int id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo actualizarVehiculo(int id, Vehiculo vehiculoActualizado) {

        Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findById(id);

        if (vehiculoExistente.isPresent()) {

            if (vehiculoActualizado.getTipo() == null) {
                throw new RuntimeException("El tipo de vehículo es obligatorio");
            }

            Vehiculo vehiculo = vehiculoExistente.get();

            vehiculo.setPlaca(vehiculoActualizado.getPlaca());
            vehiculo.setTipo(vehiculoActualizado.getTipo());

            if (vehiculoActualizado.getUsuario() != null) {

                int idUsuario = vehiculoActualizado.getUsuario().getIdUsuario();

                Usuario usuario = usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar el vehículo. El usuario con ID "
                                + idUsuario + " no existe"
                        ));

                vehiculo.setUsuario(usuario);
            }

            return vehiculoRepository.save(vehiculo);
        }

        return null;
    }

    public boolean eliminarVehiculo(int id) {

        Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findById(id);

        if (vehiculoExistente.isPresent()) {
            vehiculoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}