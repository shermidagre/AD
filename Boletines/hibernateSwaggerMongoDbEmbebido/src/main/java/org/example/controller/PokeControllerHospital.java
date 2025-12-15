package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Adestrador;
import org.example.service.PokeServiceHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PokeControllerHospital.MAPPING)
public class PokeControllerHospital {

    public static final String MAPPING = "/mongodb/pokehospital";

    @Autowired
    private PokeServiceHospital pokeServiceHospital;

    @Operation(summary = "Crear un nuevo adestrador")
    @PostMapping("/guardarAdestrador")
    public ResponseEntity<Adestrador> gardarAdestrador(@RequestBody Adestrador adestrador) {
        pokeServiceHospital.gardarAdestrador(adestrador);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Listar todos os adestradores")
    @GetMapping("/listarAdestradores")
    public ResponseEntity<List<Adestrador>> listarTodosOsAdestrador()  {
        List <Adestrador> adestradores = pokeServiceHospital.listarTodos();
        return new ResponseEntity<>(adestradores, HttpStatus.OK);
    }

    @Operation(summary = "Borrar todos os adestradores")
    @DeleteMapping ("/borrarTodosOsAdestradores")
    public ResponseEntity<Adestrador> borrarTodosOsAdestradores() {
        pokeServiceHospital.borrarTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un adestrador")
    @PutMapping("/actualizarAdestrador/{nome}")
    public ResponseEntity<Adestrador> actualizarAdestrador(@PathVariable String nome, @RequestBody Adestrador adestradorDetails) {
        Adestrador adestrador = pokeServiceHospital.encontrarAdestradorPorNome(nome, adestradorDetails);
        try {
            if (adestrador != null) {
                adestrador.setNome(adestradorDetails.getNome());
                adestrador.setCidade(adestradorDetails.getCidade());
                adestrador.setPokemon(adestradorDetails.getPokemon());
                pokeServiceHospital.gardarAdestrador(adestrador);
                return new ResponseEntity<>(adestrador, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el Adestrador: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
