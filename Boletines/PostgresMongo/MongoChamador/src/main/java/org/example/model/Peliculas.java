package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "peliculas")
public class Peliculas {
    @MongoId(FieldType.INT64) // Use INT64 for long IDs
    private Long idPelicula;
    private String titulo;
    private String xenero;
    private int ano;
    private List<Actores> actores;
}