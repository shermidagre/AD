package Serializacion_e_XML.src;

import java.io.Serializable;

public class Producto implements Serializable {
    String nombre;
    double precio;
    int cantidad;
    String ruta = "C:\\Users\\Usuario\\Desktop\\Boletines\\Serializacion_e_XML\\producto.txt";

    public Producto (String nombre, double precio, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }

}
