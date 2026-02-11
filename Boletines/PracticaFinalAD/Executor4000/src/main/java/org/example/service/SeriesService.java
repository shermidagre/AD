package PracticaFinalAD.Executor4000.src.main.java.org.example.service;

import com.google.gson.Gson;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Series;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    @Autowired
    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    public Optional<Series> findById(Long id) {
        return seriesRepository.findById(id);
    }

    public Series save(Series series) {
        return seriesRepository.save(series);
    }

    public boolean existsById(Long id) {
        return seriesRepository.existsById(id);
    }

    public void deleteById(Long id) {
        seriesRepository.deleteById(id);
    }

    public Optional<List<Series>> findByTitulo(String titulo){
        return seriesRepository.findByTitulo(titulo);
    }

    public Optional<List<Series>> deleteByTitulo(String titulo){
        return seriesRepository.deleteByTitulo(titulo);
    }
    public void exportarJson(){
        Gson gson = new Gson();
        List<Series> series = findAll();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/Series.json")){
            String json = gson.toJson(series);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}