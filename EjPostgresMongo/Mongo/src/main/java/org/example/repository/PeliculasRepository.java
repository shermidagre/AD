package org.example.repository;

import org.example.model.ParteEnriquecida;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculasRepository extends MongoRepository<ParteEnriquecida, Long>  {

}
