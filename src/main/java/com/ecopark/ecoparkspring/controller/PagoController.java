package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Pago;
import com.ecopark.ecoparkspring.service.PagoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los pagos del sistema EcoPark.
 */
@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "http://localhost:5173")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    /**
     * Crear un nuevo pago.
     */
    @PostMapping
    public ResponseEntity<?> crearPago(
            @Valid @RequestBody Pago pago) {

        try {

            Pago nuevoPago =
                    pagoService.guardarPago(pago);

            return new ResponseEntity<>(
                    nuevoPago,
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
     * Obtener todos los pagos registrados.
     */
    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {

        return new ResponseEntity<>(
                pagoService.listarPagos(),
                HttpStatus.OK
        );
    }

    /**
     * Obtener un pago por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPagoPorId(
            @PathVariable int id) {

        Optional<Pago> pago =
                pagoService.buscarPorId(id);

        if (pago.isPresent()) {

            return new ResponseEntity<>(
                    pago.get(),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "El pago con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Actualizar un pago existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(
            @PathVariable int id,
            @Valid @RequestBody Pago pago) {

        try {

            Pago actualizado =
                    pagoService.actualizarPago(id, pago);

            if (actualizado != null) {

                return new ResponseEntity<>(
                        actualizado,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "No se puede actualizar. El pago con ID "
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
     * Eliminar un pago por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(
            @PathVariable int id) {

        boolean eliminado =
                pagoService.eliminarPago(id);

        if (eliminado) {

            return new ResponseEntity<>(
                    "Pago eliminado correctamente",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "No se puede eliminar. El pago con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}