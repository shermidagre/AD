package PracticaFinalAD.Executor4000.src.main.java.org.example;

import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Personaxe;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.SanrioUniverse;
import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Series;
import org.example.service.ConexionService;
import org.example.service.SanrioUniverseService;
import org.example.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    @Autowired
    private ConexionService conexionService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private SanrioUniverseService sanrioUniverseService;

    public void executar() {

        ArrayList<Personaxe> personaxes = new ArrayList<>();
        Personaxe p1 = new Personaxe();
        p1.setNome("Hellow Kity");
        p1.setTipo("protagonista");
        personaxes.add(p1);
        System.out.println("Personaje creado" + p1);

        Personaxe p2 = new Personaxe();
        p2.setNome("My Melody");
        p2.setTipo("amiga");
        personaxes.add(p2);

        System.out.println("Personaje creado" + p2);


        Personaxe p3 = new Personaxe();
        p3.setNome("Kuromi");
        p3.setTipo("rival");
        personaxes.add(p3);

        System.out.println("Personaje creado" + p3);

        Personaxe p4 = new Personaxe();
        p4.setNome("Cinnamoroll");
        p4.setTipo("amigo");
        personaxes.add(p4);

        System.out.println("Personaje creado" + p4);

        Series series = new Series();
        series.setTitulo("Hellow Kitty & Friends");
        series.setAnolanzamento(2010);
        series.setAmbientacion("Sanrio Town");
        series.setPublico("Todos os publicos");
        series.setPersonaxes(personaxes);

        System.out.println("Serie creada");

        series = conexionService.insertAll(series);

        System.out.println("Serie insertada");


        System.out.println("Ej : Ler os datos da serie con id = 2, con todos os seus personaxes en PostgreSQL, e inserilos\n" +
                "nun documento en MongoDB.");
        Series series1 = conexionService.searchById(2L);
        System.out.println("prueba");
        seriesService.save(series1);

        System.out.println("Ler os datos da serie co título = “My Melody Adventures”, con todos os seus personaxes en\n" +
                "PostgreSQL, e inserilos nun documento en MongoDB");
        Series serieTitulo = conexionService.getByTitulo("My Melody Adventures");
        seriesService.save(serieTitulo);

        List<Series> series2 = conexionService.findAll();
        for (Series s:series2){
            seriesService.save(s);
            System.out.println("Guardado" + s);
        }

        SanrioUniverse sanrioUniverse = new SanrioUniverse();
        sanrioUniverse.setSeries(series);
        sanrioUniverseService.save(sanrioUniverse);

        System.out.println("Crear en MongoDB un documento por cada serie, cos datos completos da serie e os seus\n" +
                "personaxes asociados.\n");
        sanrioUniverseService.exportarJson();
        seriesService.exportarJson();

        System.out.println("Archivos exportados");


    }
}
