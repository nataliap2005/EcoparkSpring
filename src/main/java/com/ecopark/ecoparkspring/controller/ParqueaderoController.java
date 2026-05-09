package com.ecopark.ecoparkspring.controller;

import com.ecopark.ecoparkspring.model.Parqueadero;
import com.ecopark.ecoparkspring.service.ParqueaderoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los parqueaderos del sistema EcoPark.
 */
@RestController
@RequestMapping("/parqueaderos")
@CrossOrigin(origins = "http://localhost:5173")
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService parqueaderoService;

    /**
     * Crear un nuevo parqueadero.
     */
    @PostMapping
    public ResponseEntity<?> crearParqueadero(
            @Valid @RequestBody Parqueadero parqueadero) {

        Parqueadero nuevoParqueadero =
                parqueaderoService.guardarParqueadero(parqueadero);

        return new ResponseEntity<>(
                nuevoParqueadero,
                HttpStatus.CREATED
        );
    }

    /**
     * Obtener todos los parqueaderos registrados.
     */
    @GetMapping
    public ResponseEntity<List<Parqueadero>> listarParqueaderos() {

        List<Parqueadero> parqueaderos =
                parqueaderoService.listarParqueaderos();

        return new ResponseEntity<>(
                parqueaderos,
                HttpStatus.OK
        );
    }

    /**
     * Obtener un parqueadero por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarParqueaderoPorId(
            @PathVariable int id) {

        Optional<Parqueadero> parqueadero =
                parqueaderoService.buscarPorId(id);

        if (parqueadero.isPresent()) {

            return new ResponseEntity<>(
                    parqueadero.get(),
                    HttpStatus.OK
            );

        }

        return new ResponseEntity<>(
                "El parqueadero con ID " + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Actualizar un parqueadero existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarParqueadero(
            @PathVariable int id,
            @Valid @RequestBody Parqueadero parqueadero) {

        Parqueadero actualizado =
                parqueaderoService.actualizarParqueadero(id, parqueadero);

        if (actualizado != null) {

            return new ResponseEntity<>(
                    actualizado,
                    HttpStatus.OK
            );

        }

        return new ResponseEntity<>(
                "No se puede actualizar. El parqueadero con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Eliminar un parqueadero por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarParqueadero(
            @PathVariable int id) {

        boolean eliminado =
                parqueaderoService.eliminarParqueadero(id);

        if (eliminado) {

            return new ResponseEntity<>(
                    "Parqueadero eliminado correctamente",
                    HttpStatus.OK
            );

        }

        return new ResponseEntity<>(
                "No se puede eliminar. El parqueadero con ID "
                + id + " no existe",
                HttpStatus.NOT_FOUND
        );
    }
}