package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "actores")
public class Actores {
    private Long idActor;
    private String nome;
    private String apelidos;
    private String nacionalidade;
}