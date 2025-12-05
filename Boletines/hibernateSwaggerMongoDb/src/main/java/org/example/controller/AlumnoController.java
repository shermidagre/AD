package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Alumno;
import org.example.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AlumnoController.MAPPING)
public class AlumnoController {

    public static final String MAPPING = "/api";

    @Autowired
    private AlumnoService alumnoService;

    @Operation(summary = "Crear un nuevo alumno")
    @PostMapping("/alumno")
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
        try {
            return ResponseEntity.ok(alumnoService.crearOactualizarAlumno(alumno));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener todos los alumnos")
    @GetMapping("/alumnos")
    public List<Alumno> obtenerTodosOsAlumnos() {
        return alumnoService.obtenerTodosOsAlumnos();
    }

    @Operation(summary = "Obtener alumno por ID")
    @GetMapping("/alumno/{id}")
    public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Long id) {
        return alumnoService.obtenerAlumnoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un alumno")
    @PutMapping("/alumno/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetails) {
        try {
            Alumno actualizado = alumnoService.actualizarAlumnoExistente(id, alumnoDetails);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un alumno")
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        if (alumnoService.eliminarAlumno(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
