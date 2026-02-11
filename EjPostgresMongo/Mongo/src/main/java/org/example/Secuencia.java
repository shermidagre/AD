package org.example;

import org.example.dto.ParteDTO;
import org.example.dto.PersonajeDTO;
import org.example.model.ParteEnriquecida;
import org.example.model.PersonajeConStand;
import org.example.model.Stand;
import org.example.service.ConexionService;
import org.example.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Secuencia {

    private final ConexionService conexionService;
    private final PeliculasService peliculasService; // Este servicio ahora maneja ParteEnriquecida

    @Autowired
    public Secuencia(ConexionService conexionService, PeliculasService peliculasService) {
        this.conexionService = conexionService;
        this.peliculasService = peliculasService;
    }

    public void executar() {
        System.out.println("--- INICIANDO SECUENCIA JOJO'S ---");

        // 1. Limpiar la base de datos de Mongo para un inicio limpio
        System.out.println("Limpiando base de datos MongoDB...");
        peliculasService.borrarPeliculases();

        // 2. Usar ConexionService para crear datos en el SERVIDOR POSTGRES
        System.out.println("Creando datos en el servidor Postgres...");
        ParteDTO parte3 = new ParteDTO();
        parte3.setNombre("Stardust Crusaders");
        parte3.setAnio(1989);
        parte3 = conexionService.createParte(parte3);
        System.out.println("Parte creada: " + parte3.getNombre());

        PersonajeDTO jotaro = new PersonajeDTO();
        jotaro.setNombre("Jotaro Kujo");
        jotaro.setNacionalidad("Japanese");
        jotaro.setParte(parte3);
        jotaro = conexionService.createPersonaje(jotaro);
        System.out.println("Personaje creado: " + jotaro.getNombre());

        // 3. Usar ConexionService para obtener los datos de Postgres
        System.out.println("Obteniendo datos completos desde Postgres...");
        ParteDTO parteDesdePostgres = conexionService.getParteById(parte3.getId());

        // 4. Lógica de negocio: Enriquecer los datos con Stands
        System.out.println("Enriqueciendo los datos con Stands...");
        ParteEnriquecida parteParaMongo = new ParteEnriquecida();
        parteParaMongo.setId(parteDesdePostgres.getId());
        parteParaMongo.setNombre(parteDesdePostgres.getNombre());
        parteParaMongo.setAnio(parteDesdePostgres.getAnio());

        List<PersonajeConStand> personajesConStand = new ArrayList<>();
        if (parteDesdePostgres.getPersonajes() != null) {
            for (PersonajeDTO pDTO : parteDesdePostgres.getPersonajes()) {
                PersonajeConStand pcs = new PersonajeConStand();
                pcs.setId(pDTO.getId());
                pcs.setNombre(pDTO.getNombre());
                pcs.setNacionalidad(pDTO.getNacionalidad());
                // Lógica para asignar un Stand
                if (pDTO.getNombre().equals("Jotaro Kujo")) {
                    pcs.setStand(new Stand("Star Platinum", "Detiene el tiempo"));
                } else {
                    pcs.setStand(new Stand("N/A", "Sin Stand conocido"));
                }
                personajesConStand.add(pcs);
            }
        }
        parteParaMongo.setPersonajes(personajesConStand);

        // 5. Guardar el objeto enriquecido en nuestra base de datos MongoDB
        System.out.println("Guardando objeto enriquecido en MongoDB...");
        peliculasService.crearActualizarPeliculas(parteParaMongo);

        // 6. Usar el método bajarJson para verificar el resultado
        System.out.println("Generando archivo JSON con los datos de MongoDB...");
        peliculasService.bajarJson();

        System.out.println("--- SECUENCIA JOJO'S COMPLETADA ---");
    }
}
