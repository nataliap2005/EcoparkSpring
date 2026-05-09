package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Reserva;
import com.ecopark.ecoparkspring.service.ReservaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> crearReserva(
            @Valid @RequestBody Reserva reserva) {

        try {

            Reserva nuevaReserva =
                    reservaService.guardarReserva(reserva);

            return new ResponseEntity<>(
                    nuevaReserva,
                    HttpStatus.CREATED
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {

        return new ResponseEntity<>(
                reservaService.listarReservas(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReservaPorId(
            @PathVariable int id) {

        Optional<Reserva> reserva =
                reservaService.buscarPorId(id);

        if (reserva.isPresent()) {

            return new ResponseEntity<>(
                    reserva.get(),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "La reserva con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(
            @PathVariable int id,
            @Valid @RequestBody Reserva reserva) {

        try {

            Reserva actualizada =
                    reservaService.actualizarReserva(id, reserva);

            if (actualizada != null) {

                return new ResponseEntity<>(
                        actualizada,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "No se puede actualizar. La reserva con ID "
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(
            @PathVariable int id) {

        boolean eliminada =
                reservaService.eliminarReserva(id);

        if (eliminada) {

            return new ResponseEntity<>(
                    "Reserva eliminada correctamente",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "No se puede eliminar. La reserva con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}