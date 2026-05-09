package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.Horario;
import com.ecopark.ecoparkspring.model.Parqueadero;
import com.ecopark.ecoparkspring.repository.HorarioRepository;
import com.ecopark.ecoparkspring.repository.ParqueaderoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public Horario guardarHorario(Horario horario) {

        if (horario.getParqueadero() == null) {
            throw new RuntimeException("Debe asociar un parqueadero al horario");
        }

        int idParqueadero = horario.getParqueadero().getIdParqueadero();

        Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                .orElseThrow(() -> new RuntimeException(
                        "No se puede crear el horario. El parqueadero con ID "
                        + idParqueadero + " no existe"
                ));

        validarHoras(horario);

        horario.setParqueadero(parqueadero);

        return horarioRepository.save(horario);
    }

    public List<Horario> listarHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> buscarPorId(int id) {
        return horarioRepository.findById(id);
    }

    public Horario actualizarHorario(int id, Horario horarioActualizado) {

        Optional<Horario> horarioExistente = horarioRepository.findById(id);

        if (horarioExistente.isPresent()) {

            Horario horario = horarioExistente.get();

            validarHoras(horarioActualizado);

            horario.setDiaSemana(horarioActualizado.getDiaSemana());
            horario.setHoraApertura(horarioActualizado.getHoraApertura());
            horario.setHoraCierre(horarioActualizado.getHoraCierre());

            if (horarioActualizado.getParqueadero() != null) {

                int idParqueadero = horarioActualizado.getParqueadero().getIdParqueadero();

                Parqueadero parqueadero = parqueaderoRepository.findById(idParqueadero)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar el horario. El parqueadero con ID "
                                + idParqueadero + " no existe"
                        ));

                horario.setParqueadero(parqueadero);
            }

            return horarioRepository.save(horario);
        }

        return null;
    }

    public boolean eliminarHorario(int id) {

        Optional<Horario> horarioExistente = horarioRepository.findById(id);

        if (horarioExistente.isPresent()) {
            horarioRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private void validarHoras(Horario horario) {

        if (horario.getHoraApertura() != null
                && horario.getHoraCierre() != null
                && !horario.getHoraCierre().isAfter(horario.getHoraApertura())) {

            throw new RuntimeException(
                    "La hora de cierre debe ser posterior a la hora de apertura"
            );
        }
    }
}