package org.example.model;


public class Pokemon {

    private String id_pokemon;

    private String nome;
    private String[] tipo;
    private int nivel;
    private String[] habilidades;

    public Pokemon() {}

    public Pokemon(String nome, String[] tipo, int nivel, String[] habilidades) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.habilidades = habilidades;
    }

    public String getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(String id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getTipo() {
        return tipo;
    }

    public void setTipo(String[] tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String[] habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id_pokemon='" + id_pokemon + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo=" + String.join(",", tipo) +
                ", nivel=" + nivel +
                ", habilidades=" + String.join(",", habilidades) +
                '}';
    }
}