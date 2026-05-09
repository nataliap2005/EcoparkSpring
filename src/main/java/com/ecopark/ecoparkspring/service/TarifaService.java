package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Parqueadero;
import com.ecopark.ecoparkspring.model.Tarifa;
import com.ecopark.ecoparkspring.repository.ParqueaderoRepository;
import com.ecopark.ecoparkspring.repository.TarifaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public Tarifa guardarTarifa(Tarifa tarifa) {

        if (tarifa.getParqueadero() == null) {
            throw new RuntimeException("Debe asociar un parqueadero a la tarifa");
        }

        int idParqueadero = tarifa.getParqueadero().getIdParqueadero();

        Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                .orElseThrow(() -> new RuntimeException(
                        "No se puede crear la tarifa. El parqueadero con ID "
                        + idParqueadero + " no existe"
                ));

        tarifa.setParqueadero(parqueadero);

        return tarifaRepository.save(tarifa);
    }

    public List<Tarifa> listarTarifas() {
        return tarifaRepository.findAll();
    }

    public Optional<Tarifa> buscarPorId(int id) {
        return tarifaRepository.findById(id);
    }

    public Tarifa actualizarTarifa(int id, Tarifa tarifaActualizada) {

        Optional<Tarifa> tarifaExistente = tarifaRepository.findById(id);

        if (tarifaExistente.isPresent()) {

            Tarifa tarifa = tarifaExistente.get();

            tarifa.setTipoVehiculo(tarifaActualizada.getTipoVehiculo());
            tarifa.setValorHora(tarifaActualizada.getValorHora());
            tarifa.setDinamico(tarifaActualizada.isDinamico());

            if (tarifaActualizada.getParqueadero() != null) {

                int idParqueadero = tarifaActualizada.getParqueadero().getIdParqueadero();

                Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar la tarifa. El parqueadero con ID "
                                + idParqueadero + " no existe"
                        ));

                tarifa.setParqueadero(parqueadero);
            }

            return tarifaRepository.save(tarifa);
        }

        return null;
    }

    public boolean eliminarTarifa(int id) {

        Optional<Tarifa> tarifaExistente = tarifaRepository.findById(id);

        if (tarifaExistente.isPresent()) {
            tarifaRepository.deleteById(id);
            return true;
        }

        return false;
    }
}