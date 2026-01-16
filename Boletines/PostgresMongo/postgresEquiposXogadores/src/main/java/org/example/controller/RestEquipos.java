package org.example.controller;

import org.example.model.Equipo;
import org.example.model.Xogador;
import org.example.service.EquipoService;
import org.example.service.XogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RestEquipos.MAPPING)
public class RestEquipos {

    public static final String MAPPING = "/postgres/equipos";

    @Autowired
    private EquipoService equipoService;
    @Autowired
    private XogadorService xogadorService;

    @GetMapping
    public List<Equipo> getAll() {
        return equipoService.obterTodosEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getById(@PathVariable Long id) {
        return equipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipo> create(@RequestBody Equipo equipo) { //acepta crear xogadores no equipo porque se crea 1º equipo
        Equipo gardado = equipoService.save(equipo);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(@PathVariable Long id,
                                         @RequestBody Equipo datos) {
      /*  return equipoService.findById(id)
                .map(e -> {
                    e.setNome(datos.getNome());
                    e.setCidade(datos.getCidade());
                    return ResponseEntity.ok(equipoService.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
        */

        var equipoOptional= equipoService.findById(id);
        if(equipoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Equipo equipoToUpdate = equipoOptional.get();
        equipoToUpdate.setCidade(datos.getCidade());
        equipoToUpdate.setNome(datos.getNome());
        equipoToUpdate = equipoService.save(equipoToUpdate);

        return ResponseEntity.ok(equipoToUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!equipoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
