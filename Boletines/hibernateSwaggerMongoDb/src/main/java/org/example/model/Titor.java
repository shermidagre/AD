package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "titores")
public class Titor {

    @Id // En Mongo el ID suele ser String (ObjectId)
    @Schema(hidden = true)
    private String id_titor;

    private String nome;
    private String apelidos;

    public Titor() {}

    public Titor(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    public String getId_titor() { return id_titor; }
    public void setId_titor(String id_titor) { this.id_titor = id_titor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getApelidos() { return apelidos; }
    public void setApelidos(String apelidos) { this.apelidos = apelidos; }

}