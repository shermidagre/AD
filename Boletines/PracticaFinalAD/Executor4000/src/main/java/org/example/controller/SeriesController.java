package PracticaFinalAD.Executor4000.src.main.java.org.example.controller;

import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Series;
import org.example.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(SeriesController.MAPPING)
public class SeriesController {

    public static final String MAPPING = "/postgres/series";

    @Autowired
    private SeriesService seriesService;


    @GetMapping
    public List<Series> getAll() {
        return seriesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> getById(@PathVariable Long id) {
        return seriesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public Optional<List<Series>> findByTitulo(@PathVariable String titulo) {
        return seriesService.findByTitulo(titulo);
    }

    @DeleteMapping("/titulo/{titulo}")
    public Optional<List<Series>> deleteByTitulo(@PathVariable String titulo) {
        return seriesService.deleteByTitulo(titulo);
    }

    @PostMapping
    public ResponseEntity<Series> create(@RequestBody Series series) {
        Series gardado = seriesService.save(series);
        return ResponseEntity.ok(gardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!seriesService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        seriesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
