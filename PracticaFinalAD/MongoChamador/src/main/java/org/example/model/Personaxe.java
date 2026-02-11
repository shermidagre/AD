package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Personaxe {

    private Long idpersonaxe;

    private String nome;

    private String tipo;

    private Series serie;

    // Getters y setters


    public Long getIdpersonaxe() {
        return idpersonaxe;
    }

    public void setIdpersonaxe(Long idpersonaxe) {
        this.idpersonaxe = idpersonaxe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }
}
