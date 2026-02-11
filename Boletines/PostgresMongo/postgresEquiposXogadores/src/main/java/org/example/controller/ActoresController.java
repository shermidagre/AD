package org.example.controller;

import org.example.model.Peliculas;
import org.example.model.Actores;
import org.example.service.PeliculasService;
import org.example.service.ActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ActoresController.MAPPING)
public class ActoresController {

    public static final String MAPPING = "/postgres/xogadores";

    @Autowired
    private PeliculasService peliculasService;
    @Autowired
    private ActoresService actoresService;


    @GetMapping
    public List<Actores> getAll() {
        return actoresService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actores> getById(@PathVariable Long id) {
        return actoresService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actores> create(@RequestBody Actores actores) { //xogamos con recoller o equipo e metelo en cada xogador
/*{
  "nome": "string",
  "apelidos": "string",
  "posicion": "string",
  "dataNacemento": "2026-01-15",
  "nacionalidade": "string",
  "equipo": {
    "id": "1"
  }
}*/
        if (actores.getPeliculas() != null && actores.getPeliculas().getIdPelicula() != null) {
            Peliculas eq = peliculasService.findById(actores.getPeliculas().getIdPelicula())
                    .orElse(null);
            if (eq == null) {
                return ResponseEntity.badRequest().build();
            }
            actores.setPeliculas(eq);
        }
        Actores gardado = actoresService.save(actores);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actores> update(@PathVariable Long id,
                                          @RequestBody Actores datos) {
        return actoresService.findById(id)
                .map(x -> {
                    x.setNome(datos.getNome());
                    x.setApelidos(datos.getApelidos());
                    x.setNacionalidade(datos.getNacionalidade());
                    x.setNacionalidade(datos.getNacionalidade());

                    if (datos.getPeliculas() != null && datos.getPeliculas().getIdPelicula() != null) {
                        Peliculas eq = peliculasService.findById(datos.getPeliculas().getIdPelicula())
                                .orElse(null);
                        x.setPeliculas(eq);
                    }

                    return ResponseEntity.ok(actoresService.save(x));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!actoresService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        actoresService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}