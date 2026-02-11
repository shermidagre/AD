package PracticaFinalAD.MongoChamador.src.main.java.org.example.repository;

import org.example.model.Personaxe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaxeRepository extends MongoRepository<Personaxe,Long> {
}
