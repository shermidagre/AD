package PracticaFinalAD.JoJosGres.src.main.java.org.example.repository;

import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<List<Series>> findByTitulo(String titulo);
    Optional<List<Series>> deleteByTitulo(String titulo);

}
