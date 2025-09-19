package Parte2.src;

import java.io.*;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws IOException {

        String ruta1 = "Input-Output_Stream/randomfoto1.jpg";
        String ruta2 = "Input-Output_Stream/randomfoto2.jpg";

        //   copiararfoto(ruta1,ruta2);
        añadirfoto(ruta1, ruta2);
    }

    public static void copiararfoto(String ruta1, String ruta2) throws IOException {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(ruta1));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ruta2));

        int caracter = 0;
        byte[] p = new byte[in.available()];
        System.out.println("mensaje de depuracion (bytes leidos) " + Arrays.toString(p));
        try {
            while (true) {
                int bytesLeidos = in.read();
                if (bytesLeidos == -1) {
                    break;
                } else {
                    byte b = (byte) bytesLeidos;
                    p[caracter] = b;
                    caracter++;

                }

            }
            System.out.println("mensaje de depuracion (ver si se copian) " + Arrays.toString(p));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        out.write(p);
        System.out.println("datos escritos en el archivo de destino.");
        in.close();
        out.close();


    }

    public static void añadirfoto(String ruta1, String ruta2) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(ruta1));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ruta2, true));
        int caracter;
        try {
            while ((caracter = in.read()) != -1) {
                out.write(caracter);
            }
            System.out.println("mensaje de depuracion (comprobar si llega a leer todos los bytes) -> " + caracter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        in.close();
        out.close();

    }
}