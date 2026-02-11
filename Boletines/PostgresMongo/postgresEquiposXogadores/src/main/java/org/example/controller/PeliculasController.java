package org.example.controller;

import org.example.model.Peliculas;
import org.example.service.PeliculasService;
import org.example.service.ActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PeliculasController.MAPPING)
public class PeliculasController {

    public static final String MAPPING = "/postgres/equipos";

    @Autowired
    private PeliculasService peliculasService;
    @Autowired
    private ActoresService actoresService;

    @GetMapping
    public List<Peliculas> getAll() {
        return peliculasService.obterTodosEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peliculas> getById(@PathVariable Long id) {
        return peliculasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Peliculas> create(@RequestBody Peliculas peliculas) { //acepta crear xogadores no equipo porque se crea 1º equipo
        Peliculas gardado = peliculasService.save(peliculas);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peliculas> update(@PathVariable Long id,
                                            @RequestBody Peliculas datos) {
      /*  return equipoService.findById(id)
                .map(e -> {
                    e.setNome(datos.getNome());
                    e.setCidade(datos.getCidade());
                    return ResponseEntity.ok(equipoService.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
        */

        var equipoOptional= peliculasService.findById(id);
        if(equipoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Peliculas peliculasToUpdate = equipoOptional.get();
        peliculasToUpdate.setXenero(datos.getXenero());
        peliculasToUpdate.setTitulo(datos.getTitulo());
        peliculasToUpdate = peliculasService.save(peliculasToUpdate);

        return ResponseEntity.ok(peliculasToUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!peliculasService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        peliculasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Peliculas> getByTitulo(@PathVariable String titulo) {
        return peliculasService.findByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
