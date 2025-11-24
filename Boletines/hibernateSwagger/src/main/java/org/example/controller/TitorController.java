package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Titor;
import org.example.repository.TitorRepository;
import org.example.service.TitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(TitorController.MAPPING)
public class TitorController {

    public static final String MAPPING = "/api";

    @Autowired
    private TitorRepository titorRepository;
    @Autowired
    private TitorService titorService;


    @Operation(summary = "Crear unha novo titor")
    @PostMapping("/titor")
    public Titor crearTitor(@RequestBody Titor titor) {
        return titorService.crearOactualizarTitor(titor);
    }

    @Operation(summary = "Obter todos os titores")
    @GetMapping("/titores")
    public List<Titor> obtenerTodosOsTitores() {
        return titorService.obtenerTodosOsTitores();
    }

    @Operation(summary = "Obter alumnos de un titor por id")
    @GetMapping("/titor/{id}")
    public ResponseEntity<Titor> obterTitororId(@PathVariable Long id) {
        Optional<Titor> persoa = titorService.obtenerTitorPorId(id);
        return persoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un titor")
    @PutMapping("/titor/{id}")
    public ResponseEntity<Titor> actualizarTitor(@PathVariable Long id, @RequestBody Titor titorDetails) {
        Optional<Titor> persoaOptional = titorService.obtenerTitorPorId(id);
        if (persoaOptional.isPresent()) {
            Titor titor = persoaOptional.get();
            titor.setNome(titorDetails.getNome());
            titor.setApelidos(titorDetails.getApelidos());
            Titor titorActualizada = titorService.crearOactualizarTitor(titor);
            return ResponseEntity.ok(titorActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un titor")
    @DeleteMapping("/titor/{id}")
    public ResponseEntity<Void> eliminarTitor(@PathVariable Long id) {
        if (titorRepository.existsById(id)) {
            titorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
