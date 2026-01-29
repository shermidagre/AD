package org.example;

import org.example.dto.ActoresDTO;
import org.example.dto.PeliculasDTO;
import org.example.model.Actores;
import org.example.model.Peliculas;
import org.example.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Secuencia {

    private final PeliculasService peliculasService;

    @Autowired
    public Secuencia(PeliculasService peliculasService) {
        this.peliculasService = peliculasService;
    }

    public void ejecuccionSecuencia() {
        System.out.println("Iniciando secuencia de operaciones...");

        // 1. Insertar datos de 2 películas con 3 actores cada una en PostgreSQL.
        System.out.println("1. Insertando películas con actores en PostgreSQL...");

        // Pelicula 1 DTO
        PeliculasDTO pelicula1DTO = new PeliculasDTO();
        pelicula1DTO.setTitulo("El Señor de los Anillos: La Comunidad del Anillo");
        pelicula1DTO.setXenero("Fantasia");
        pelicula1DTO.setAno(2001);

        ActoresDTO actor1_1_DTO = new ActoresDTO();
        actor1_1_DTO.setNome("Elijah");
        actor1_1_DTO.setApelidos("Wood");
        actor1_1_DTO.setNacionalidade("Estadounidense");
        actor1_1_DTO.setPeliculas(pelicula1DTO); // Link back to parent

        ActoresDTO actor1_2_DTO = new ActoresDTO();
        actor1_2_DTO.setNome("Ian");
        actor1_2_DTO.setApelidos("McKellen");
        actor1_2_DTO.setNacionalidade("Británico");
        actor1_2_DTO.setPeliculas(pelicula1DTO); // Link back to parent

        ActoresDTO actor1_3_DTO = new ActoresDTO();
        actor1_3_DTO.setNome("Orlando");
        actor1_3_DTO.setApelidos("Bloom");
        actor1_3_DTO.setNacionalidade("Británico");
        actor1_3_DTO.setPeliculas(pelicula1DTO); // Link back to parent

        pelicula1DTO.setActores(Arrays.asList(actor1_1_DTO, actor1_2_DTO, actor1_3_DTO));


        // Pelicula 2 DTO
        PeliculasDTO pelicula2DTO = new PeliculasDTO();
        pelicula2DTO.setTitulo("Pulp Fiction");
        pelicula2DTO.setXenero("Crimen");
        pelicula2DTO.setAno(1994);

        ActoresDTO actor2_1_DTO = new ActoresDTO();
        actor2_1_DTO.setNome("John");
        actor2_1_DTO.setApelidos("Travolta");
        actor2_1_DTO.setNacionalidade("Estadounidense");
        actor2_1_DTO.setPeliculas(pelicula2DTO); // Link back to parent

        ActoresDTO actor2_2_DTO = new ActoresDTO();
        actor2_2_DTO.setNome("Uma");
        actor2_2_DTO.setApelidos("Thurman");
        actor2_2_DTO.setNacionalidade("Estadounidense");
        actor2_2_DTO.setPeliculas(pelicula2DTO); // Link back to parent

        ActoresDTO actor2_3_DTO = new ActoresDTO();
        actor2_3_DTO.setNome("Samuel L.");
        actor2_3_DTO.setApelidos("Jackson");
        actor2_3_DTO.setNacionalidade("Estadounidense");
        actor2_3_DTO.setPeliculas(pelicula2DTO); // Link back to parent

        pelicula2DTO.setActores(Arrays.asList(actor2_1_DTO, actor2_2_DTO, actor2_3_DTO));


        PeliculasDTO createdPelicula1DTO = peliculasService.insertMovieIntoPostgres(pelicula1DTO);
        PeliculasDTO createdPelicula2DTO = peliculasService.insertMovieIntoPostgres(pelicula2DTO);
        System.out.println("Películas insertadas en PostgreSQL.");

        // 2. Leer los datos de una película, consultándola por id, con todos sus actores en postgres
        //    e insertarla en mongodb.
        System.out.println("2. Leyendo película por ID de PostgreSQL e insertando en MongoDB...");
        if (createdPelicula1DTO != null && createdPelicula1DTO.getIdPelicula() != null) {
            Optional<PeliculasDTO> peliculaByIdDTO = peliculasService.getMovieFromPostgresById(createdPelicula1DTO.getIdPelicula());
            peliculaByIdDTO.ifPresent(dto -> {
                Peliculas peliculaMongo = peliculasService.convertToMongoPeliculas(dto);
                peliculasService.crearPeliculaMongo(peliculaMongo);
                System.out.println("Película '" + peliculaMongo.getTitulo() + "' (ID: " + peliculaMongo.getIdPelicula() + ") insertada en MongoDB.");
            });
        } else {
            System.out.println("No se pudo obtener la película creada por ID de PostgreSQL.");
        }


        // 3. Leer los datos de una película, consultándola por nombre, con todos sus actores en postgres
        //    e insertarla en mongodb.
        System.out.println("3. Leyendo película por nombre de PostgreSQL e insertando en MongoDB...");
        Optional<PeliculasDTO> peliculaByTitleDTO = peliculasService.getMovieFromPostgresByTitle("Pulp Fiction");
        peliculaByTitleDTO.ifPresent(dto -> {
            Peliculas peliculaMongo = peliculasService.convertToMongoPeliculas(dto);
            peliculasService.crearPeliculaMongo(peliculaMongo);
            System.out.println("Película '" + peliculaMongo.getTitulo() + "' (ID: " + peliculaMongo.getIdPelicula() + ") insertada en MongoDB.");
        });

        // 4. Leer los datos de películas en mongodb, con sus actores, y exportarlas en un archivo JSON
        System.out.println("4. Exportando películas de MongoDB a JSON...");
        peliculasService.exportarJson();
        System.out.println("Películas exportadas a insert.json.");

        System.out.println("Secuencia de operaciones completada.");
    }
}

