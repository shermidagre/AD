import java.sql.Connection;
import java.sql.SQLException;

public class InsertarUnidade {
        static void insertarUnidade(Unidade unidade) throws SQLException {

            Connection connection = null;

            connection = Conexion.conectar();
            System.out.println("Conexión exitosa a la base de datos.");
            String sql = "INSERT INTO adeptasororitas (cod, nome, puntos) VALUES ('" + unidade.getCod()
                    + "', '" + unidade.getNome() + "', "
                    + unidade.getPuntos() + ")";

            try {
                connection.createStatement().execute(sql);
                String confirmacion = "Unidade " + unidade.getNome() + " inserida correctamente.";
                System.out.println(confirmacion);

            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        }
}
