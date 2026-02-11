package org.example.controller;

import org.example.model.Partes;
import org.example.service.PartesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestPartes.MAPPING)
public class RestPartes {

    public static final String MAPPING = "/jojo/partes";

    @Autowired
    private PartesService partesService;

    @GetMapping
    public List<Partes> getAll() {
        return partesService.obtenerTodasLasPartes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partes> getById(@PathVariable Long id) {
        return partesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Partes>> getByNombre(@PathVariable String nombre){
        List<Partes> p = partesService.obtenerPartesPorNombre(nombre);
        if (p == null || p.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Partes> create(@RequestBody Partes parte) {
        Partes guardado = partesService.save(parte);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partes> update(@PathVariable Long id, @RequestBody Partes datos) {
        return partesService.findById(id)
                .map(parteExistente -> {
                    parteExistente.setNombre(datos.getNombre());
                    parteExistente.setGenero(datos.getGenero());
                    parteExistente.setAnio(datos.getAnio());
                    Partes actualizado = partesService.save(parteExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!partesService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        partesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
