package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Vehiculo;
import com.ecopark.ecoparkspring.service.VehiculoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los vehículos del sistema EcoPark.
 */
@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins = "http://localhost:5173")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    /**
     * Crear un nuevo vehículo.
     */
    @PostMapping
    public ResponseEntity<?> crearVehiculo(
            @Valid @RequestBody Vehiculo vehiculo) {

        try {

            Vehiculo nuevoVehiculo =
                    vehiculoService.guardarVehiculo(vehiculo);

            return new ResponseEntity<>(
                    nuevoVehiculo,
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
     * Obtener todos los vehículos registrados.
     */
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listarVehiculos() {

        List<Vehiculo> vehiculos =
                vehiculoService.listarVehiculos();

        return new ResponseEntity<>(
                vehiculos,
                HttpStatus.OK
        );
    }

    /**
     * Obtener un vehículo por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVehiculoPorId(
            @PathVariable int id) {

        Optional<Vehiculo> vehiculo =
                vehiculoService.buscarPorId(id);

        if (vehiculo.isPresent()) {

            return new ResponseEntity<>(
                    vehiculo.get(),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "El vehículo con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Actualizar un vehículo existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVehiculo(
            @PathVariable int id,
            @Valid @RequestBody Vehiculo vehiculo) {

        try {

            Vehiculo actualizado =
                    vehiculoService.actualizarVehiculo(id, vehiculo);

            if (actualizado != null) {

                return new ResponseEntity<>(
                        actualizado,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "No se puede actualizar. El vehículo con ID "
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
     * Eliminar un vehículo por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVehiculo(
            @PathVariable int id) {

        boolean eliminado =
                vehiculoService.eliminarVehiculo(id);

        if (eliminado) {

            return new ResponseEntity<>(
                    "Vehículo eliminado correctamente",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "No se puede eliminar. El vehículo con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}