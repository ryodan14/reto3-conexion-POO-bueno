import java.sql.*;

public class ConexionBaseDeDatos {
    public static void getConexion(String url, String user, String password){
        try {
            // Conexión a la base de datos
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
            conn.close();
        } catch (SQLException e) {
            //Printea el error SQL
            e.printStackTrace();
        }
    }
}
