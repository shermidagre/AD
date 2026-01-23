package org.example.repository;

import org.example.model.Actores;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActoresRepository extends MongoRepository<Actores, String> {
}
