
public class LogService {
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

    static void leerArchivo() {

        new objtectOutputStream(new FileOutputStream("log.txt"));
        try {
            System.out.println("Contenido del archivo:");

            Scanner myReader = new Scanner(archivo);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
        }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }

    }

}