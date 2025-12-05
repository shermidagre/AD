package org.example.repository;

import org.example.model.Titor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitorRepository extends MongoRepository<Titor, String> {
}