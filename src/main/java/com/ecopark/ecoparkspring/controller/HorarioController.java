package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Horario;
import com.ecopark.ecoparkspring.service.HorarioService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los horarios del sistema EcoPark.
 */
@RestController
@RequestMapping("/horarios")
@CrossOrigin(origins = "http://localhost:5173")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    /**
     * Crear un nuevo horario.
     */
    @PostMapping
    public ResponseEntity<?> crearHorario(
            @Valid @RequestBody Horario horario) {

        try {

            Horario nuevoHorario =
                    horarioService.guardarHorario(horario);

            return new ResponseEntity<>(
                    nuevoHorario,
                    HttpStatus.CREATED
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Obtener todos los horarios registrados.
     */
    @GetMapping
    public ResponseEntity<List<Horario>> listarHorarios() {

        List<Horario> horarios =
                horarioService.listarHorarios();

        return new ResponseEntity<>(
                horarios,
                HttpStatus.OK
        );
    }

    /**
     * Obtener un horario por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarHorarioPorId(
            @PathVariable int id) {

        Optional<Horario> horario =
                horarioService.buscarPorId(id);

        if (horario.isPresent()) {

            return new ResponseEntity<>(
                    horario.get(),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "El horario con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Actualizar un horario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHorario(
            @PathVariable int id,
            @Valid @RequestBody Horario horario) {

        try {

            Horario actualizado =
                    horarioService.actualizarHorario(id, horario);

            if (actualizado != null) {

                return new ResponseEntity<>(
                        actualizado,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "No se puede actualizar. El horario con ID "
                    + id + " no existe",
                    HttpStatus.NOT_FOUND
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Eliminar un horario por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHorario(
            @PathVariable int id) {

        boolean eliminado =
                horarioService.eliminarHorario(id);

        if (eliminado) {

            return new ResponseEntity<>(
                    "Horario eliminado correctamente",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "No se puede eliminar. El horario con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}