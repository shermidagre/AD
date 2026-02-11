package org.example.controller;

import org.example.model.Partes;
import org.example.model.Personajes;
import org.example.service.PartesService;
import org.example.service.PersonajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestPersonajes.MAPPING)
public class RestPersonajes {

    public static final String MAPPING = "/jojo/personajes";

    @Autowired
    private PartesService partesService;
    @Autowired
    private PersonajesService personajesService;

    @GetMapping
    public List<Personajes> getAll() {
        return personajesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personajes> getById(@PathVariable Long id) {
        return personajesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personajes> create(@RequestBody Personajes personaje) {
        if (personaje.getParte() != null && personaje.getParte().getId_pelicula() != null) {
            Partes parte = partesService.findById(personaje.getParte().getId_pelicula())
                    .orElse(null);
            if (parte == null) {
                return ResponseEntity.badRequest().build();
            }
            personaje.setParte(parte);
        }
        Personajes guardado = personajesService.save(personaje);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personajes> update(@PathVariable Long id, @RequestBody Personajes datos) {
        return personajesService.findById(id)
                .map(personajeExistente -> {
                    personajeExistente.setNombre(datos.getNombre());
                    personajeExistente.setApellidos(datos.getApellidos());
                    personajeExistente.setNacionalidad(datos.getNacionalidad());

                    if (datos.getParte() != null && datos.getParte().getId_pelicula() != null) {
                        Partes parte = partesService.findById(datos.getParte().getId_pelicula())
                                .orElse(null);
                        personajeExistente.setParte(parte);
                    }

                    return ResponseEntity.ok(personajesService.save(personajeExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personajesService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personajesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}