package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.repository.PokemonRepository;
import org.example.repository.AdestradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Pokemon crearOactualizarAlumno(Pokemon pokemon) {
        String idTitor = pokemon.getIdTitor();

        if (idTitor == null) {
            throw new IllegalArgumentException("El alumno debe tener un id_titor asociado.");
        }

        Adestrador adestrador = adestradorRepository.findById(idTitor)
                .orElseThrow(() -> new EntityNotFoundException("Titor con ID " + idTitor + " no encontrado."));

        pokemon.setTitor(adestrador);

        return pokemonRepository.save(pokemon);
    }

    @Transactional
    public Pokemon actualizarAlumnoExistente(Long id, Pokemon nuevoPokemon) {
        Pokemon pokemonExistente = pokemonRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));

        pokemonExistente.setNome(nuevoPokemon.getNome());
        pokemonExistente.setTipo(nuevoPokemon.getTipo());

        // Si se quiere cambiar de titor también
        if(nuevoPokemon.getIdTitor() != null) {
            Adestrador nuevoAdestrador = adestradorRepository.findById(nuevoPokemon.getIdTitor())
                    .orElseThrow(() -> new EntityNotFoundException("Nuevo Titor no encontrado"));
            pokemonExistente.setTitor(nuevoAdestrador);
        }

        return pokemonRepository.save(pokemonExistente);
    }

    public List<Pokemon> obtenerTodosOsAlumnos() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> obtenerAlumnoPorId(Long id) {
        return pokemonRepository.findById(String.valueOf(id));
    }

    public boolean eliminarAlumno(Long id) {
        if (pokemonRepository.existsById(String.valueOf(id))) {
            pokemonRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}