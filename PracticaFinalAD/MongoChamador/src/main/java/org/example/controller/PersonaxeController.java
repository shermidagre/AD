package org.example.controller;


import org.example.model.Personaxe;
import org.example.service.PersonaxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(PersonaxeController.MAPPING)
public class PersonaxeController {

    public static final String MAPPING = "/postgres/personaxes";

    @Autowired
    private PersonaxeService personaxeService;


    @GetMapping
    public List<Personaxe> getAll() {
        return personaxeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaxe> getById(@PathVariable Long id) {
        return personaxeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personaxe> create(@RequestBody Personaxe personaxe) {
        Personaxe gardado = personaxeService.save(personaxe);
        return ResponseEntity.ok(gardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personaxeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personaxeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}