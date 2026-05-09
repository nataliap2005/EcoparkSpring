package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Parqueadero;
import com.ecopark.ecoparkspring.model.Reserva;
import com.ecopark.ecoparkspring.model.Usuario;
import com.ecopark.ecoparkspring.repository.ParqueaderoRepository;
import com.ecopark.ecoparkspring.repository.ReservaRepository;
import com.ecopark.ecoparkspring.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public Reserva guardarReserva(Reserva reserva) {

        validarHoras(reserva);

        if (reserva.getEstado() == null) {
            throw new RuntimeException("El estado de la reserva es obligatorio");
        }

        if (reserva.getUsuario() == null) {
            throw new RuntimeException("Debe asociar un usuario a la reserva");
        }

        if (reserva.getParqueadero() == null) {
            throw new RuntimeException("Debe asociar un parqueadero a la reserva");
        }

        int idUsuario = reserva.getUsuario().getIdUsuario();

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException(
                        "El usuario con ID " + idUsuario + " no existe"
                ));

        int idParqueadero = reserva.getParqueadero().getIdParqueadero();

        Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                .orElseThrow(() -> new RuntimeException(
                        "El parqueadero con ID " + idParqueadero + " no existe"
                ));

        reserva.setUsuario(usuario);
        reserva.setParqueadero(parqueadero);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(int id) {
        return reservaRepository.findById(id);
    }

    public Reserva actualizarReserva(int id, Reserva reservaActualizada) {

        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {

            validarHoras(reservaActualizada);

            if (reservaActualizada.getEstado() == null) {
                throw new RuntimeException("El estado de la reserva es obligatorio");
            }

            Reserva reserva = reservaExistente.get();

            reserva.setFecha(reservaActualizada.getFecha());
            reserva.setHoraInicio(reservaActualizada.getHoraInicio());
            reserva.setHoraFin(reservaActualizada.getHoraFin());
            reserva.setEstado(reservaActualizada.getEstado());

            return reservaRepository.save(reserva);
        }

        return null;
    }

    public boolean eliminarReserva(int id) {

        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private void validarHoras(Reserva reserva) {

        if (reserva.getHoraInicio() != null
                && reserva.getHoraFin() != null
                && !reserva.getHoraFin().isAfter(reserva.getHoraInicio())) {

            throw new RuntimeException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }
    }
}