package org.example.controller;

import org.example.model.Equipo;
import org.example.model.Xogador;
import org.example.service.EquipoService;
import org.example.service.XogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestXogadores.MAPPING)
public class RestXogadores {

    public static final String MAPPING = "/postgres/xogadores";

    @Autowired
    private EquipoService equipoService;
    @Autowired
    private XogadorService xogadorService;


    @GetMapping
    public List<Xogador> getAll() {
        return xogadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Xogador> getById(@PathVariable Long id) {
        return xogadorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Xogador> create(@RequestBody Xogador xogador) { //xogamos con recoller o equipo e metelo en cada xogador
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
        if (xogador.getEquipo() != null && xogador.getEquipo().getId() != null) {
            Equipo eq = equipoService.findById(xogador.getEquipo().getId())
                    .orElse(null);
            if (eq == null) {
                return ResponseEntity.badRequest().build();
            }
            xogador.setEquipo(eq);
        }
        Xogador gardado = xogadorService.save(xogador);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Xogador> update(@PathVariable Long id,
                                          @RequestBody Xogador datos) {
        return xogadorService.findById(id)
                .map(x -> {
                    x.setNome(datos.getNome());
                    x.setApelidos(datos.getApelidos());
                    x.setPosicion(datos.getPosicion());
                    x.setDataNacemento(datos.getDataNacemento());
                    x.setNacionalidade(datos.getNacionalidade());

                    if (datos.getEquipo() != null && datos.getEquipo().getId() != null) {
                        Equipo eq = equipoService.findById(datos.getEquipo().getId())
                                .orElse(null);
                        x.setEquipo(eq);
                    }

                    return ResponseEntity.ok(xogadorService.save(x));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!xogadorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        xogadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}