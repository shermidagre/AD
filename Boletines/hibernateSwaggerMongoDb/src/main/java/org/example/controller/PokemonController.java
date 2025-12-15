package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Pokemon;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PokemonController.MAPPING)
public class PokemonController {

    public static final String MAPPING = "/api";

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Crear un nuevo pokemon")
    @PostMapping("/pokemon")
    public ResponseEntity<?> crearPokemon(@RequestBody Pokemon pokemon) {
        try {
            return ResponseEntity.ok(pokemonService.crearPokemon(pokemon));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener todos los pokemons")
    @GetMapping("/pokemon")
    public List<Pokemon> obtenerPokemons() {
        return pokemonService.obtenerPokemons();
    }

    @Operation(summary = "Obtener pokemon por ID")
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<Pokemon> obtenerPokemonId(@PathVariable String id) {
        return pokemonService.obtenerPokemonId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un pokemon")
    @PutMapping("/pokemon/{id}")
    public ResponseEntity<?> actualizarPokemon(@PathVariable String id, @RequestBody Pokemon pokemonDetails) {
        try {
            Pokemon actualizado = pokemonService.actualizarPokemon(id, pokemonDetails);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un pokemon")
    @DeleteMapping("/pokemon/{id}")
    public ResponseEntity<Void> eliminarPokemonId(@PathVariable String id) {
        if (pokemonService.eliminarPokemonId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
