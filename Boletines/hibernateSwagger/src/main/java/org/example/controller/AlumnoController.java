package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Alumno;
import org.example.repository.AlumnoRepository;
import org.example.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AlumnoController.MAPPING)
public class AlumnoController {

    public static final String MAPPING = "/api";

    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private AlumnoService alumnoService;


    @Operation(summary = "Crear unha novo alumno")
    @PostMapping("/alumno")
    public Alumno crearOactualizarAlumno(@RequestBody Alumno alumno) {
        return alumnoService.crearOactualizarAlumno(alumno);
    }

    @Operation(summary = "Obter todos os alumnos")
    @GetMapping("/alumnos")
    public List<Alumno> obtenerTodosOsAlumnos() {
        return alumnoService.obtenerTodosOsAlumnos();
    }

    @Operation(summary = "Obter alumno por ID")
    @GetMapping("/alumno/{id}")
    public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);
        return alumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un alumno")
    @PutMapping("/alumno/{id}")
    public ResponseEntity<Alumno> actualizarTitor(@PathVariable Long id, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> persoaOptional = alumnoService.obtenerAlumnoPorId(id);
        if (persoaOptional.isPresent()) {
            Alumno alumno = persoaOptional.get();
            alumno.setNome(alumnoDetails.getNome());
            alumno.setApelidos(alumnoDetails.getApelidos());
            Alumno alumnoActualizada = alumnoService.crearOactualizarAlumno(alumno);
            return ResponseEntity.ok(alumnoActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un alumno")
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
