package org.example.model;

import java.util.List;

public class Equipo {

    private Long id;

    private String nome;
    private String cidade;
    private List<Xogador> xogadores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Xogador> getXogadores() {
        return xogadores;
    }

    public void setXogadores(List<Xogador> xogadores) {
        this.xogadores = xogadores;
    }
}
