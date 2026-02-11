package org.example.dto;

import java.util.List;

// DTO para recibir datos de Partes desde el servidor Postgres
public class ParteDTO {
    private Long id;
    private String nombre;
    private int anio;
    private List<PersonajeDTO> personajes;
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public List<PersonajeDTO> getPersonajes() { return personajes; }
    public void setPersonajes(List<PersonajeDTO> personajes) { this.personajes = personajes; }
}
