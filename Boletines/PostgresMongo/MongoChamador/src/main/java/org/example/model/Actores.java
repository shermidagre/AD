package org.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "actores")
public class Actores {

    private long idActor;
    private String nome;
    private String apelidos;
    private String nacionalidad;



    // Getters y Setters

    public long getIdActor() {
        return idActor;
    }

    public void setIdActor(long idActor) {
        this.idActor = idActor;
    }

    public String getApelidos() {
        return apellido;
    }

    public void setApelidos(String apelidos) {
        this.apellido = apellido;
    }

    public String getNome() {
        return nombre;
    }

    public void setNome(String nome) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }




}
