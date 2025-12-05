package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Titor;
import org.example.service.TitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TitorController.MAPPING)
public class TitorController {

    public static final String MAPPING = "/api";

    @Autowired
    private TitorService titorService;

    @Operation(summary = "Crear un nuevo titor")
    @PostMapping("/titor")
    public Titor crearTitor(@RequestBody Titor titor) {
        return titorService.crearOactualizarTitor(titor);
    }

    @Operation(summary = "Obtener todos los titores")
    @GetMapping("/titores")
    public List<Titor> obtenerTodosOsTitores() {
        return titorService.obtenerTodosOsTitores();
    }

    @Operation(summary = "Obtener titor por id")
    @GetMapping("/titor/{id}")
    public ResponseEntity<Titor> obtenerTitorPorId(@PathVariable Long id) {
        return titorService.obtenerTitorPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un titor")
    @PutMapping("/titor/{id}")
    public ResponseEntity<Titor> actualizarTitor(@PathVariable Long id, @RequestBody Titor titorDetails) {
        try {
            Titor actualizado = titorService.actualizarTitorExistente(id, titorDetails);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un titor")
    @DeleteMapping("/titor/{id}")
    public ResponseEntity<Void> eliminarTitor(@PathVariable Long id) {
        if (titorService.eliminarTitor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}