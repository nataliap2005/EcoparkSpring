package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.IngresoSalida;
import com.ecopark.ecoparkspring.service.IngresoSalidaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los ingresos y salidas de vehículos.
 */
@RestController
@RequestMapping("/ingresos-salidas")
@CrossOrigin(origins = "http://localhost:5173")
public class IngresoSalidaController {

    @Autowired
    private IngresoSalidaService ingresoSalidaService;

    /**
     * Crear un nuevo movimiento de ingreso o salida.
     */
    @PostMapping
    public ResponseEntity<?> crearIngresoSalida(
            @Valid @RequestBody IngresoSalida ingresoSalida) {

        try {

            IngresoSalida nuevoMovimiento =
                    ingresoSalidaService.guardarIngresoSalida(ingresoSalida);

            return new ResponseEntity<>(
                    nuevoMovimiento,
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
     * Obtener todos los movimientos registrados.
     */
    @GetMapping
    public ResponseEntity<List<IngresoSalida>> listarIngresosSalidas() {

        return new ResponseEntity<>(
                ingresoSalidaService.listarIngresosSalidas(),
                HttpStatus.OK
        );
    }

    /**
     * Obtener un movimiento por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarIngresoSalidaPorId(
            @PathVariable int id) {

        Optional<IngresoSalida> movimiento =
                ingresoSalidaService.buscarPorId(id);

        if (movimiento.isPresent()) {

            return new ResponseEntity<>(
                    movimiento.get(),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "El movimiento con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Actualizar un movimiento existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarIngresoSalida(
            @PathVariable int id,
            @Valid @RequestBody IngresoSalida ingresoSalida) {

        try {

            IngresoSalida actualizado =
                    ingresoSalidaService.actualizarIngresoSalida(id, ingresoSalida);

            if (actualizado != null) {

                return new ResponseEntity<>(
                        actualizado,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "No se puede actualizar. El movimiento con ID "
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
     * Eliminar un movimiento por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarIngresoSalida(
            @PathVariable int id) {

        boolean eliminado =
                ingresoSalidaService.eliminarIngresoSalida(id);

        if (eliminado) {

            return new ResponseEntity<>(
                    "Movimiento eliminado correctamente",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "No se puede eliminar. El movimiento con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}