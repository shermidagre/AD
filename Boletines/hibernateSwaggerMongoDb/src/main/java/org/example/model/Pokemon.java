package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pokemon")
public class Pokemon {

    @Id
    @Schema(hidden = true)
    private String id_pokemon;

    private String nome;
    private String[] tipo;
    private int nivel;
    private String[] habilidades;

    // Relación con Adestrador (Solo guarda la referencia en Mongo)
    @DBRef
    @JsonIgnore // Ignoramos el objeto completo al serializar a JSON para evitar bucles o datos enormes
    private Adestrador adestrador;

    // Campo auxiliar para recibir/enviar solo el ID en el JSON
    @Transient
    private String adestradorIdRequest;

    public Pokemon() {}

    public Pokemon(String nome, String[] tipo, int nivel, String[] habilidades) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.habilidades = habilidades;
    }

    // --- Getters y Setters Básicos ---

    public String getId_pokemon() { return id_pokemon; }
    public void setId_pokemon(String id_pokemon) { this.id_pokemon = id_pokemon; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String[] getTipo() { return tipo; }
    public void setTipo(String[] tipo) { this.tipo = tipo; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public String[] getHabilidades() { return habilidades; }
    public void setHabilidades(String[] habilidades) { this.habilidades = habilidades; }

    // --- Gestión de la Relación Adestrador ---

    // Getter y Setter del OBJETO real (usado por el Servicio y Spring Data)
    public Adestrador getAdestrador() { return adestrador; }
    public void setAdestrador(Adestrador adestrador) { this.adestrador = adestrador; }


    // --- Gestión del JSON (DTO pattern integrado) ---

    // GETTER JSON: Cuando enviamos el Pokemon al frontend, enviamos el ID del adestrador
    @JsonProperty("id_adestrador")
    public String getIdAdestrador() {
        // Si tenemos el objeto cargado, devolvemos su ID. Si no, devolvemos el temporal.
        if (this.adestrador != null) {
            return this.adestrador.getId_adestrador();
        }
        return this.adestradorIdRequest;
    }

    // SETTER JSON: Cuando recibimos un JSON para crear/editar, guardamos el ID temporalmente
    @JsonProperty("id_adestrador")
    public void setIdAdestrador(String idAdestrador) {
        this.adestradorIdRequest = idAdestrador;
    }
}