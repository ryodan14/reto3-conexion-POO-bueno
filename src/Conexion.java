import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion{
    public static void main(String[] args) {
        String url = "jdbc:mysql://25.7.162.154:3306/bdbiblioteca";
        String user = "admin";
        String password = "1234";

        try {
            // Conexión a la base de datos
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
