import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    // Configura los parámetros de conexión
    private static final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            // Paso 1: Establecer la conexión a la base de datos
            // El `DriverManager` busca y carga el controlador JDBC
            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(url, user, contraseña);

            // Paso 2: Si la conexión es exitosa, se imprime un mensaje de confirmación
            System.out.println("¡Conexión exitosa a la base de datos!");

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            // Paso 4: Asegurarse de que la conexión se cierre
            if (conexion != null) {
                try {
                    conexion.close();
                    System.out.println("Conexión a la base de datos cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}
