package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Parqueadero;
import com.ecopark.ecoparkspring.repository.ParqueaderoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParqueaderoService {

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public Parqueadero guardarParqueadero(Parqueadero parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    public List<Parqueadero> listarParqueaderos() {
        return parqueaderoRepository.findAll();
    }

    public Optional<Parqueadero> buscarPorId(int id) {
        return parqueaderoRepository.findById(id);
    }

    public Parqueadero actualizarParqueadero(int id, Parqueadero parqueaderoActualizado) {
        Optional<Parqueadero> parqueaderoExistente = parqueaderoRepository.findById(id);

        if (parqueaderoExistente.isPresent()) {
            Parqueadero parqueadero = parqueaderoExistente.get();

            parqueadero.setNombre(parqueaderoActualizado.getNombre());
            parqueadero.setDireccion(parqueaderoActualizado.getDireccion());
            parqueadero.setGpsLat(parqueaderoActualizado.getGpsLat());
            parqueadero.setGpsLng(parqueaderoActualizado.getGpsLng());
            parqueadero.setTipo(parqueaderoActualizado.getTipo());
            parqueadero.setCapacidadCarros(parqueaderoActualizado.getCapacidadCarros());
            parqueadero.setCapacidadMotos(parqueaderoActualizado.getCapacidadMotos());

            return parqueaderoRepository.save(parqueadero);
        }

        return null;
    }

    public boolean eliminarParqueadero(int id) {
        Optional<Parqueadero> parqueaderoExistente = parqueaderoRepository.findById(id);

        if (parqueaderoExistente.isPresent()) {
            parqueaderoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}