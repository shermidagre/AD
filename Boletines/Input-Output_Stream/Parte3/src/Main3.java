package Parte3.src;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main3 {
    public static void main(String[] args) throws FileNotFoundException {

        String ruta = "Input-Output_Stream/texto3.txt";
        duplicarTexto(ruta);
    }
    static void duplicarTexto(String ruta) throws FileNotFoundException, RuntimeException {
        DataInputStream in = new DataInputStream(new FileInputStream(ruta));
        DataOutputStream out = new DataOutputStream(new FileOutputStream(ruta));
        try {

            String texto = in.readUTF();
            System.out.println("mensaje de depuracion (Texto leido): " + texto);

            System.out.println("o size e" + out.size());
            texto = "a";
            String r = texto + texto;
            out.writeUTF(r);

            System.out.println("mensaje de depuracion (Texto escrito): " + texto);

            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
