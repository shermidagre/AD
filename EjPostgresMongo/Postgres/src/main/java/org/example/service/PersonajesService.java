package org.example.service;

import org.example.model.Personajes;
import org.example.repository.PersonajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajesService {
    @Autowired
    private final PersonajesRepository personajesRepository;

    public PersonajesService(PersonajesRepository personajesRepository) {
        this.personajesRepository = personajesRepository;
    }
    public List<Personajes> findAll(){
        return personajesRepository.findAll();
    }
    public Optional<Personajes> findById(Long id) {
        return personajesRepository.findById(id);
    }

    public Personajes save(Personajes personaje) {
        return personajesRepository.save(personaje);
    }

    public boolean existsById(Long id) {
        return personajesRepository.existsById(id);
    }

    public void deleteById(Long id) {
        personajesRepository.deleteById(id);
    }
}
