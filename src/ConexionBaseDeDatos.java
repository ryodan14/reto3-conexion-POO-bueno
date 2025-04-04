import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {

    public static void mensa(String mensaje){
        System.out.println(mensaje);
    }

    public static Connection ConexionBaseDeDatos(String url, String user, String password){
        Connection conn = null;
        try {
            // Conexión a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            //Printea el error SQL
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean cerrarConexion(Connection conn){
        boolean dev=true;
        try {
            conn.close();
        } catch (Exception e) {
            dev=false;
        }
        return dev;
    }
}