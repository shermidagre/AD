import java.sql.*;

public class Parte3 {
    // Configura los parámetros de conexión
    private static final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";


    public static void main(String[] args) {
permisos();
    }
    static void permisos (){
        try (Connection conexion = DriverManager.getConnection(url, user, contraseña);
             Statement stmt = conexion.createStatement()) {

            ResultSet rs = stmt.executeQuery("select * from anime");
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Descripcion: " + rs.getString("descripcion"));
                System.out.println("Data: " + rs.getDate("data"));
                System.out.println("Puntuacion: " + rs.getInt("puntuacion"));
                System.out.println("---------------------------");
            }
            int insert = stmt.executeUpdate("insert into anime (nome, descripcion, data, puntuacion) values ('Naruto', 'Anime de ninjas', '2002-10-03', 9)");
            System.out.println(insert + " filas insertadas.");

            rs = stmt.executeQuery("select nome from anime");
            while (rs.next()){
                System.out.println("Nome: " + rs.getString("nome"));
            }

            int update = stmt.executeUpdate("update anime set puntuacion = 10 where nome = 'Naruto'");
            System.out.println(update + " filas actualizadas.");

            rs = stmt.executeQuery("select puntuacion from anime");
            while (rs.next()){
                System.out.println("Puntuacion: " + rs.getInt("puntuacion"));
            }
            int delete = stmt.executeUpdate("delete from anime where nome = 'Naruto'");
            System.out.println(delete + " filas eliminadas.");

            rs = stmt.executeQuery("select * from anime");
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Descripcion: " + rs.getString("descripcion"));
                System.out.println("Data: " + rs.getDate("data"));
                System.out.println("Puntuacion: " + rs.getInt("puntuacion"));
                System.out.println("---------------------------");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
