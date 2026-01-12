package org.example.repository;

import org.example.model.Shopping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends MongoRepository<Shopping, String> {
}