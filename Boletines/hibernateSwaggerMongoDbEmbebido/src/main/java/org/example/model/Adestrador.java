package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adestrador")
public class Adestrador {

    @Id
    @Schema(hidden = true)
    private String id_adestrador;

    private String nome;
    private String cidade;

    private Pokemon pokemon;
    public Adestrador() {}

    public Adestrador(String nome, String cidade, Pokemon pokemon) {
        this.nome = nome;
        this.cidade = cidade;
        this.pokemon = pokemon;
    }

    public String getId_adestrador() { return id_adestrador; }
    public void setId_adestrador(String id_adestrador) { this.id_adestrador = id_adestrador; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "Adestrador{" +
                "id_adestrador='" + id_adestrador + '\'' +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", pokemon=" + pokemon +
                '}';
    }

}