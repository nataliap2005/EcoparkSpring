package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.IngresoSalida;
import com.ecopark.ecoparkspring.model.Parqueadero;
import com.ecopark.ecoparkspring.model.Vehiculo;
import com.ecopark.ecoparkspring.repository.IngresoSalidaRepository;
import com.ecopark.ecoparkspring.repository.ParqueaderoRepository;
import com.ecopark.ecoparkspring.repository.VehiculoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoSalidaService {

    @Autowired
    private IngresoSalidaRepository ingresoSalidaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public IngresoSalida guardarIngresoSalida(IngresoSalida ingresoSalida) {

        validarHoras(ingresoSalida);

        if (ingresoSalida.getVehiculo() == null) {
            throw new RuntimeException("Debe asociar un vehículo al movimiento");
        }

        if (ingresoSalida.getParqueadero() == null) {
            throw new RuntimeException("Debe asociar un parqueadero al movimiento");
        }

        int idVehiculo = ingresoSalida.getVehiculo().getIdVehiculo();

        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException(
                        "No se puede crear el movimiento. El vehículo con ID "
                        + idVehiculo + " no existe"
                ));

        int idParqueadero = ingresoSalida.getParqueadero().getIdParqueadero();

        Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                .orElseThrow(() -> new RuntimeException(
                        "No se puede crear el movimiento. El parqueadero con ID "
                        + idParqueadero + " no existe"
                ));

        ingresoSalida.setVehiculo(vehiculo);
        ingresoSalida.setParqueadero(parqueadero);

        return ingresoSalidaRepository.save(ingresoSalida);
    }

    public List<IngresoSalida> listarIngresosSalidas() {
        return ingresoSalidaRepository.findAll();
    }

    public Optional<IngresoSalida> buscarPorId(int id) {
        return ingresoSalidaRepository.findById(id);
    }

    public IngresoSalida actualizarIngresoSalida(int id, IngresoSalida ingresoSalidaActualizado) {

        Optional<IngresoSalida> movimientoExistente =
                ingresoSalidaRepository.findById(id);

        if (movimientoExistente.isPresent()) {

            validarHoras(ingresoSalidaActualizado);

            IngresoSalida movimiento = movimientoExistente.get();

            movimiento.setHoraIngreso(ingresoSalidaActualizado.getHoraIngreso());
            movimiento.setHoraSalida(ingresoSalidaActualizado.getHoraSalida());
            movimiento.setEspacioAsignado(ingresoSalidaActualizado.getEspacioAsignado());

            if (ingresoSalidaActualizado.getVehiculo() != null) {

                int idVehiculo = ingresoSalidaActualizado.getVehiculo().getIdVehiculo();

                Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar el movimiento. El vehículo con ID "
                                + idVehiculo + " no existe"
                        ));

                movimiento.setVehiculo(vehiculo);
            }

            if (ingresoSalidaActualizado.getParqueadero() != null) {

                int idParqueadero = ingresoSalidaActualizado.getParqueadero().getIdParqueadero();

                Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar el movimiento. El parqueadero con ID "
                                + idParqueadero + " no existe"
                        ));

                movimiento.setParqueadero(parqueadero);
            }

            return ingresoSalidaRepository.save(movimiento);
        }

        return null;
    }

    public boolean eliminarIngresoSalida(int id) {

        Optional<IngresoSalida> movimientoExistente =
                ingresoSalidaRepository.findById(id);

        if (movimientoExistente.isPresent()) {
            ingresoSalidaRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private void validarHoras(IngresoSalida ingresoSalida) {

        if (ingresoSalida.getHoraIngreso() != null
                && ingresoSalida.getHoraSalida() != null
                && !ingresoSalida.getHoraSalida().isAfter(ingresoSalida.getHoraIngreso())) {

            throw new RuntimeException(
                    "La hora de salida debe ser posterior a la hora de ingreso"
            );
        }
    }
}