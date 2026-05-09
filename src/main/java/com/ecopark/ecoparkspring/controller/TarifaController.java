package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Tarifa;
import com.ecopark.ecoparkspring.service.TarifaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las tarifas del sistema EcoPark.
 */
@RestController
@RequestMapping("/tarifas")
@CrossOrigin(origins = "http://localhost:5173")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    /**
     * Crear una nueva tarifa.
     */
    @PostMapping
    public ResponseEntity<?> crearTarifa(
            @Valid @RequestBody Tarifa tarifa) {

        try {

            Tarifa nuevaTarifa =
                    tarifaService.guardarTarifa(tarifa);

            return new ResponseEntity<>(
                    nuevaTarifa,
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
     * Obtener todas las tarifas registradas.
     */
    @GetMapping
    public ResponseEntity<List<Tarifa>> listarTarifas() {

        List<Tarifa> tarifas =
                tarifaService.listarTarifas();

        return new ResponseEntity<>(
                tarifas,
                HttpStatus.OK
        );
    }

    /**
     * Obtener una tarifa por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTarifaPorId(
            @PathVariable int id) {

        Optional<Tarifa> tarifa =
                tarifaService.buscarPorId(id);

        if (tarifa.isPresent()) {

            return new ResponseEntity<>(
                    tarifa.get(),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "La tarifa con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Actualizar una tarifa existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTarifa(
            @PathVariable int id,
            @Valid @RequestBody Tarifa tarifa) {

        try {

            Tarifa actualizada =
                    tarifaService.actualizarTarifa(id, tarifa);

            if (actualizada != null) {

                return new ResponseEntity<>(
                        actualizada,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "No se puede actualizar. La tarifa con ID "
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
     * Eliminar una tarifa por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarifa(
            @PathVariable int id) {

        boolean eliminada =
                tarifaService.eliminarTarifa(id);

        if (eliminada) {

            return new ResponseEntity<>(
                    "Tarifa eliminada correctamente",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "No se puede eliminar. La tarifa con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}