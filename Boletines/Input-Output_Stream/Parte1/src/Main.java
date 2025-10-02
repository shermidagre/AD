package Parte1.src;

import java.io.*;
import java.util.Arrays;

public class Main  {

    public static void main(String[] args) throws IOException {

        //File prueba = new File("texto1.txt");
        //System.out.println(prueba.length() + " bytes");
        String ruta1 = "Input-Output_Stream/texto1.txt";
        String ruta2 = "Input-Output_Stream/texto2.txt";

       /*
        creararchivoEj1("texto2.txt","Tanjiro\n" +
                "Nezuko\n" +
                "Muzan\n");
        */

        // copiararachivoEj2(ruta1,ruta2);
        añadircontenidoEj3(ruta1,ruta2);
        //copiararchivoEj2.1("texto1.txt", "texto3.txt");
    }
    public static void creararchivoEj1(String ruta, String contenido) {
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copiararachivoEj2(String ruta, String ruta2) throws IOException {

        FileInputStream in = new FileInputStream(ruta);
        FileOutputStream out = new FileOutputStream(ruta2);

        int caracter= 0;
        byte[] p = new byte[in.available()];
        System.out.println("mensaje de depuracion (bytes leidos) " + Arrays.toString(p));
        try {
            while (true){
                int bytesLeidos = in.read();
                if (bytesLeidos == -1) {
                    break;
                }else {
                    byte b = (byte) bytesLeidos;
                    p[caracter] = b;
                    caracter++;
                }
            }
            System.out.println("mensaje de depuracion (ver si se copian) " + Arrays.toString(p));

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        out.write(p);
        System.out.println("datos escritos en el archivo de destino.");
        in.close();
        out.close();

    }


    /*
        public static void copiararchivoEj2.1( String ruta, String ruta2) throws IOException {

        try (FileReader fr = new FileReader(ruta);FileWriter fw2 = new FileWriter(ruta2)) {
            int caracter;
            // Leemos el primer carácter y lo usamos como condición del bucle.
            // El bucle se ejecuta mientras `read()` no devuelva -1 (final del archivo).
            while ((caracter = fr.read()) != -1) {
                fw2.write(caracter); // Escribimos el carácter en el archivo de destino
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Archivo copiado correctamente.");


        }
     */
    public static void añadircontenidoEj3 (String ruta, String ruta2) throws IOException {
        FileInputStream in = new FileInputStream(ruta);
        FileOutputStream out = new FileOutputStream(ruta2,true);
        int caracter;
        try {
            while ((caracter = in.read())!= -1) {
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


