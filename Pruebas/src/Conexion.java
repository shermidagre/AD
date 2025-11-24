
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Configura los parámetros de conexión
    private static final String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres";
    private static final String user = "postgres.qqhaxudmcbztqtiqrayu";
    private static final String contraseña = "proyectoACOP";

    public static void main(String[] args) throws SQLException {
        Conexionbd();
    }
    static Connection Conexionbd() throws SQLException {
        Connection conexion = null;
        try {

            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(url, user, contraseña);

            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {
            System.out.println(e);
        }

        return conexion;
    }
}
