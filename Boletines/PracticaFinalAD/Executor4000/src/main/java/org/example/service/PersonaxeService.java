package PracticaFinalAD.Executor4000.src.main.java.org.example.service;

import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Personaxe;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.repository.PersonaxeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaxeService {

    @Autowired
    private final PersonaxeRepository personaxeRepository;

    public PersonaxeService(PersonaxeRepository personaxeRepository) {
        this.personaxeRepository = personaxeRepository;
    }
    public List<Personaxe> findAll() {
        return personaxeRepository.findAll();
    }

    public Optional<Personaxe> findById(Long id) {
        return personaxeRepository.findById(id);
    }

    public Personaxe save(Personaxe personaxe) {
        return personaxeRepository.save(personaxe);
    }

    public boolean existsById(Long id) {
        return personaxeRepository.existsById(id);
    }

    public void deleteById(Long id) {
        personaxeRepository.deleteById(id);
    }
}