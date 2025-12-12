package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alumnos")
public class Pokemon {

    @Id
    @Schema(hidden = true)
    private String id_alumno; // String para Mongo

    private String nome;
    private String[] tipo;
    private int nivel;
    private String[] habilidades;

    // @DBRef indica a Mongo que guarde solo la referencia (el ID), no el objeto entero incrustado
    @DBRef
    @JsonIgnore
    private Adestrador adestrador;

    // Campo auxiliar para recibir el ID desde el JSON del frontend
    @Transient
    private String titorIdRequest;



    public Pokemon() {}

    public Pokemon(String nome, String[] tipo, int nivel, String[] habilidades) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.habilidades = habilidades;
    }

    public String getId_alumno() { return id_alumno; }
    public void setId_alumno(String id_alumno) { this.id_alumno = id_alumno; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String[] getTipo() { return tipo; }
    public void setTipo(String[] tipo) { this.tipo = tipo; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public String[] getHabilidades() { return habilidades; }
    public void setHabilidades(String[] habilidades) { this.habilidades = habilidades; }
    public Adestrador getTitor() { return adestrador; }
    public void setTitor(Adestrador adestrador) { this.adestrador = adestrador; }

    // GETTER JSON: Devuelve el ID del titor cuando piden el alumno
    @JsonProperty("id_titor")
    public String getIdTitor() {
        if(this.titorIdRequest != null) return this.titorIdRequest;
        return (adestrador != null) ? adestrador.getId_titor() : null;
    }

    // SETTER JSON: Recibe el ID del titor cuando crean el alumno
    @JsonProperty("id_titor")
    public void setIdTitor(String idTitor) {
        this.titorIdRequest = idTitor;
    }
}