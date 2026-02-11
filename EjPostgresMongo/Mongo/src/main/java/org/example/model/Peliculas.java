package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "partes_enriquecidas")
public class ParteEnriquecida {
    @Id
    private Long id; // Mismo ID que en Postgres para fácil referencia
    private String nombre;
    private int anio;
    private List<PersonajeConStand> personajes;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public List<PersonajeConStand> getPersonajes() { return personajes; }
    public void setPersonajes(List<PersonajeConStand> personajes) { this.personajes = personajes; }

    @Override
    public String toString() {
        return "ParteEnriquecida{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", personajes=" + personajes +
                '}';
    }
}
