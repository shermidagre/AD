package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alumnos")
public class Alumno {

    @Id
    @Schema(hidden = true)
    private String id_alumno; // String para Mongo

    private String nome;
    private String apelidos;

    // @DBRef indica a Mongo que guarde solo la referencia (el ID), no el objeto entero incrustado
    @DBRef
    @JsonIgnore
    private Titor titor;

    // Campo auxiliar para recibir el ID desde el JSON del frontend
    @Transient
    private String titorIdRequest;

    public Alumno() {}

    public Alumno(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    public String getId_alumno() { return id_alumno; }
    public void setId_alumno(String id_alumno) { this.id_alumno = id_alumno; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getApelidos() { return apelidos; }
    public void setApelidos(String apelidos) { this.apelidos = apelidos; }

    public Titor getTitor() { return titor; }
    public void setTitor(Titor titor) { this.titor = titor; }

    // GETTER JSON: Devuelve el ID del titor cuando piden el alumno
    @JsonProperty("id_titor")
    public String getIdTitor() {
        if(this.titorIdRequest != null) return this.titorIdRequest;
        return (titor != null) ? titor.getId_titor() : null;
    }

    // SETTER JSON: Recibe el ID del titor cuando crean el alumno
    @JsonProperty("id_titor")
    public void setIdTitor(String idTitor) {
        this.titorIdRequest = idTitor;
    }
}