package PracticaFinalAD.MongoChamador.src.main.java.org.example.repository;

import org.example.model.Series;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends MongoRepository<Series,Long> {
    Optional<List<Series>> findByTitulo(String titulo);
    Optional<List<Series>> deleteByTitulo(String titulo);
}
