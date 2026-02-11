package org.example.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActoresDTO {
    private Long idActor;
    private String nome;
    private String apelidos;
    private String nacionalidade;

    @JsonBackReference
    private PeliculasDTO peliculas; // To match PostgreSQL entity for API communication
}
