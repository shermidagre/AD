package org.example.repository;

import org.example.model.Adestrador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokeRepositoryHospital extends MongoRepository<Adestrador, String> {
    Adestrador findByNome(String nomAdestrador);
    void deleteByNome(String nomAdestrador);
}
