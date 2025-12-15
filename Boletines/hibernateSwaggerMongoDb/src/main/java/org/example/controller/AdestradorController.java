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

    @Operation(summary = "Crear un nuevo adestrador")
    @PostMapping("/adestrador")
    public Adestrador crearAdestrador(@RequestBody Adestrador adestrador) {
        return adestradorService.crearAdestrador(adestrador);
    }

    @Operation(summary = "Obtener todos los adestradores")
    @GetMapping("/adestrador")
    public List<Adestrador> obtenerAdestradores() {
        return adestradorService.obtenerAdestradores();
    }

    @Operation(summary = "Obtener adestrador por id")
    @GetMapping("/adestrador/{id}")
    public ResponseEntity<Adestrador> obtenerAdestradorId(@PathVariable Long id) {
        return adestradorService.obtenerAdestradorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un adestrador")
    @PutMapping("/adestrador/{id}")
    public ResponseEntity<Adestrador> actualizarAdestrador(@PathVariable Long id, @RequestBody Adestrador adestradorDetails) {
        try {
            Adestrador actualizado = adestradorService.actualizarAdestrador(id, adestradorDetails);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un adestrador")
    @DeleteMapping("/adestrador/{id}")
    public ResponseEntity<Void> eliminarAdestradorId(@PathVariable Long id) {
        if (adestradorService.eliminarAdestradorId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}