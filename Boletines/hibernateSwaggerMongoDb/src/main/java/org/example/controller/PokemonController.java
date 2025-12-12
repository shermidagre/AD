package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Pokemon;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PokemonController.MAPPING)
public class PokemonController {

    public static final String MAPPING = "/api";

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Crear un nuevo pokemon")
    @PostMapping("/pokemon")
    public ResponseEntity<?> crearAlumno(@RequestBody Pokemon pokemon) {
        try {
            return ResponseEntity.ok(pokemonService.crearOactualizarAlumno(pokemon));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener todos los alumnos")
    @GetMapping("/alumnos")
    public List<Pokemon> obtenerTodosOsAlumnos() {
        return pokemonService.obtenerTodosOsAlumnos();
    }

    @Operation(summary = "Obtener alumno por ID")
    @GetMapping("/alumno/{id}")
    public ResponseEntity<Pokemon> obtenerAlumnoPorId(@PathVariable Long id) {
        return pokemonService.obtenerAlumnoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un alumno")
    @PutMapping("/alumno/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long id, @RequestBody Pokemon pokemonDetails) {
        try {
            Pokemon actualizado = pokemonService.actualizarAlumnoExistente(id, pokemonDetails);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un alumno")
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        if (pokemonService.eliminarAlumno(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
