import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {


        String fileName = "p1";
        String dirName = "/home/dam/Documentos/Samuel/AcesoDatos";
        String directorio = "arquivosdir";
        String cadea = "/home/dam/Documentos/Samuel/AcesoDatos";
        //System.out.println(eDirectorio(cadea));
        //System.out.println(eFicheiro(cadea));
        System.out.println(creaDirectorio(directorio));
        //System.out.println(crearFicheiro(dirName, fileName));
        //System.out.println(modoAcceso(dirName, fileName));
        //System.out.println(calculaLonxitude(dirName, fileName));
        //System.out.println(mLectura(dirName, fileName));
        //System.out.println(mEscritura(dirName, fileName));
        //System.out.println(borrarFicheiro(dirName, fileName));
        borrarDirectorio(dirName);
        mContido(dirName);
        recur(new File("/home/dam/Documentos/Samuel/AcesoDatos/a"));
    }

    /* Haz una funcion para mover directorios */


    public static String eDirectorio(String cadea) {
        File c = new File(cadea);

        if (c.isDirectory()) {
            return ("1. E un directorio");
        } else {
            return ("1. non e un directorio");
        }
    }


    public static String eFicheiro(String cadea) {
        File c = new File(cadea);
        if (c.isFile()) {
            return ("2. E un ficheiro");
        } else {
            return ("2. non e un ficheiro");
        }
    }


    public static String creaDirectorio(String directorio) {
        File c = new File(directorio);
        if (!c.exists()) {
            c.mkdir();
            return "3. O directorio" + directorio + " ha sido creado";
        } else {
            return "3. O directorio xa existe";
        }
    }


    public static String crearFicheiro(String dirName, String fileName) {


        File d = new File(dirName);
        File f = new File(d, fileName);

        try {
            if (f.createNewFile()) {
                System.out.println("4. Fichero creado correctamente.");
                return "fichero creado";
            } else {
                return "no se pudo crear fichero";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }


    public static String modoAcceso(String dirName, String fileName) {

        File d = new File(dirName);
        File f = new File(d, fileName);

        if (d.exists()) {

            if (f.exists()) {

                if (f.canRead()) {
                    System.out.println("se puede leer");
                } else System.out.println("no se puede leer");

                if (f.canWrite()) {
                    System.out.println("se puede escribir");
                } else System.out.println("no se puede escribir");

            } else System.out.println("el archivo no existe");

        } else System.out.println("el directorio no existe");

        return "funciona";
    }


    public static String calculaLonxitude(String dirName, String fileName) {

        File d = new File(dirName);
        File f = new File(d, fileName);

        if (d.exists()) {

            System.out.println("el directorio existe y su longitud es : " + f.length());

        } else System.out.println("el directorio no existe");
        return "funciona";
    }


    public static String mLectura(String dirName, String fileName) {


        File d = new File(dirName);
        File f = new File(d, fileName);


        if (d.exists()) {
            System.out.println("el directorio existe ");


            if (f.exists()) {
                f.setReadOnly();
                System.out.println("se le acaba de conceder el leer en el");
            } else System.out.println("el fichero no existe");


        } else System.out.println("el directorio no existe");
        return "funciona";
    }


    public static String mEscritura(String dirName, String fileName) {

        File d = new File(dirName);
        File f = new File(d, fileName);

        if (d.exists()) {

            System.out.println("el directorio existe ");

            if (f.exists()) {

                f.setWritable(true);
                System.out.println("se le acaba de conceder el permiso");

            } else System.out.println("el archivo no existe");

        } else System.out.println("el directorio no existe");
        return "funciona";
    }


    public static String borrarFicheiro(String dirName, String fileName) {

        File d = new File(dirName);
        File f = new File(d, fileName);

        if (d.exists()) {
            System.out.println("el directorio existe");

            if (f.exists()) {
                f.delete();
                System.out.println("se acaba de eliminar el archivo");
            } else System.out.println("el archivo no existe");

        } else System.out.println("el el directorio no existe");

        return "funciona";
    }
    public static String borrarDirectorio(String dirName) {

        File d = new File(dirName);

        if (d.exists()&& d.isDirectory()) {
            System.out.println("el directorio existe");

            d.delete();
            }
        else System.out.println("el el directorio no existe");


        return "funciona";
    }

    public static void mContido(String dirName) {
        File dir = new File(dirName);

        if (dir.exists() && dir.isDirectory()) {
            String[] contido = dir.list();

            if (contido != null && contido.length > 0) {
                for (String t : contido) {
                    System.out.println(t);
                }

            } else {
                System.out.println("No tiene contenido.");
            }
        }
    }
    public static void recur(File archivo) {

        if (archivo.exists()) {
            if (archivo.isDirectory()) {
                System.out.println("Directorio " + archivo.getAbsolutePath());
                File[] files = archivo.listFiles();

                if (files != null) {
                    for (File file : files) {
                        recur(file);
                    }
                }
            } else {
                System.out.println("Archivo " + archivo.getAbsolutePath());
            }
        } else {
            System.out.println("No existe");
        }
    }


}
