package org.example.repository;

import com.mongodb.client.MongoClient;
import org.example.model.Actores;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActoresRepository extends MongoRepository<Actores, String> {
}
