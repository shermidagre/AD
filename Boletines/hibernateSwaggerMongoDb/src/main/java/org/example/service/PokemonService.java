package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.repository.PokemonRepository;
import org.example.repository.AdestradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final AdestradorRepository adestradorRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, AdestradorRepository adestradorRepository) {
        this.pokemonRepository = pokemonRepository;
        this.adestradorRepository = adestradorRepository;
    }

    @Transactional
    public Pokemon crearPokemon(Pokemon pokemon) {
        String idAdestrador = pokemon.getIdAdestrador();

        if (idAdestrador == null || idAdestrador.isEmpty()) {
            throw new IllegalArgumentException("El Pokemon debe tener un id_adestrador asociado.");
        }

        Adestrador adestrador = adestradorRepository.findById(idAdestrador)
                .orElseThrow(() -> new EntityNotFoundException("Adestrador con ID " + idAdestrador + " no encontrado."));

        // Asignamos el objeto relacional
        pokemon.setAdestrador(adestrador);

        return pokemonRepository.save(pokemon);
    }

    @Transactional
    public Pokemon actualizarPokemon(String id, Pokemon nuevoPokemon) {
        Pokemon pokemonExistente = pokemonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon no encontrado con ID: " + id));

        pokemonExistente.setNome(nuevoPokemon.getNome());
        pokemonExistente.setTipo(nuevoPokemon.getTipo());
        pokemonExistente.setNivel(nuevoPokemon.getNivel());
        pokemonExistente.setHabilidades(nuevoPokemon.getHabilidades());

        String nuevoIdAdestrador = nuevoPokemon.getIdAdestrador();

        if(nuevoIdAdestrador != null && !nuevoIdAdestrador.isEmpty()) {
            Adestrador nuevoAdestrador = adestradorRepository.findById(nuevoIdAdestrador)
                    .orElseThrow(() -> new EntityNotFoundException("Nuevo Adestrador no encontrado"));

            pokemonExistente.setAdestrador(nuevoAdestrador);
            // Actualizamos también el campo request por coherencia, aunque no se guarde en DB
            pokemonExistente.setIdAdestrador(nuevoIdAdestrador);
        }

        return pokemonRepository.save(pokemonExistente);
    }

    public List<Pokemon> obtenerPokemons() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> obtenerPokemonId(String id) {
        return pokemonRepository.findById(id);
    }

    public boolean eliminarPokemonId(String id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}