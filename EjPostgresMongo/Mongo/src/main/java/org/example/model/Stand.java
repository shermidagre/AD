package org.example.model;

// DTO para el Stand
public class Stand {
    private String nombre;
    private String habilidad;

    public Stand(String nombre, String habilidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getHabilidad() { return habilidad; }
    public void setHabilidad(String habilidad) { this.habilidad = habilidad; }

    @Override
    public String toString() {
        return "Stand{" +
                "nombre='" + nombre + "'" + 
                ", habilidad='" + habilidad + "'" + 
                '}';
    }
}
