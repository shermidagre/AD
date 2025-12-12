package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Adestrador;
import org.example.service.AdestradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AdestradorController.MAPPING)
public class AdestradorController {

    public static final String MAPPING = "/api";

    @Autowired
    private AdestradorService adestradorService;

    @Operation(summary = "Crear un nuevo titor")
    @PostMapping("/titor")
    public Adestrador crearTitor(@RequestBody Adestrador adestrador) {
        return adestradorService.crearOactualizarTitor(adestrador);
    }

    @Operation(summary = "Obtener todos los titores")
    @GetMapping("/titores")
    public List<Adestrador> obtenerTodosOsTitores() {
        return adestradorService.obtenerTodosOsTitores();
    }

    @Operation(summary = "Obtener titor por id")
    @GetMapping("/titor/{id}")
    public ResponseEntity<Adestrador> obtenerTitorPorId(@PathVariable Long id) {
        return adestradorService.obtenerTitorPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un titor")
    @PutMapping("/titor/{id}")
    public ResponseEntity<Adestrador> actualizarTitor(@PathVariable Long id, @RequestBody Adestrador adestradorDetails) {
        try {
            Adestrador actualizado = adestradorService.actualizarTitorExistente(id, adestradorDetails);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un titor")
    @DeleteMapping("/titor/{id}")
    public ResponseEntity<Void> eliminarTitor(@PathVariable Long id) {
        if (adestradorService.eliminarTitor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}