package org.example.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculasDTO {
    private Long idPelicula;
    private String titulo;
    private String xenero;
    private int ano;
    @JsonManagedReference
    private List<ActoresDTO> actores;
}
