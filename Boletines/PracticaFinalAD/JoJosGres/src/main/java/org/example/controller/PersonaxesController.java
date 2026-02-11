package PracticaFinalAD.JoJosGres.src.main.java.org.example.controller;

import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Personaxe;
import PracticaFinalAD.Executor4000.src.main.java.org.example.service.PersonaxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PersonaxesController.MAPPING)
public class PersonaxesController {

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