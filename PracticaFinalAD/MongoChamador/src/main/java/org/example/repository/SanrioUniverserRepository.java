package org.example.repository;

import org.example.model.SanrioUniverse;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SanrioUniverserRepository extends MongoRepository<SanrioUniverse,String> {
}
