import java.io.*;
import java.util.Scanner;

public class lasd {
    public void log(String message) {
        System.out.println("Log: " + message);
    }

    static void crearArchivo() {

        File archivo = new File("log.txt");
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }

    }

    static void leerArchivo() throws IOException {

        new ObjectOutputStream(new FileOutputStream("log.txt"));
        System.out.println("Contenido del archivo:");

    }
    public static void main(String[] args) {
        crearArchivo();

    }

}