package PracticaFinalAD.Executor4000.src.main.java.org.example.service;

import com.google.gson.Gson;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.SanrioUniverse;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.repository.SanrioUniverserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class SanrioUniverseService {

    @Autowired
    private final SanrioUniverserRepository sanrioUniverserRepository;

    public SanrioUniverseService(SanrioUniverserRepository sanrioUniverserRepository) {
        this.sanrioUniverserRepository = sanrioUniverserRepository;
    }


    public List<SanrioUniverse> findAll() {
        return sanrioUniverserRepository.findAll();
    }


    public SanrioUniverse save(SanrioUniverse sanrioUniverse) {
        return sanrioUniverserRepository.save(sanrioUniverse);
    }

    public void exportarJson(){
        Gson gson = new Gson();
        List<SanrioUniverse> sanrioUniverses = findAll();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/Sanrio.json")){
            String json = gson.toJson(sanrioUniverses);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}
