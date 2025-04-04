import java.sql.*;
import java.util.*;

public class Conexion{
    public static void main(String[] args) {
        String url = "jdbc:mysql://25.7.162.154:3306/bdbiblioteca";
        String user = "admin";
        String password = "1234";
        Connection conn = null;
        Scanner teclado=new Scanner(System.in);

        System.out.println("*********************************************************************");
        System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
        System.out.println("*********************************************************************\n");
        System.out.println("       1-Obtener conexión                 4-Cerrar conexión");


        int eleccion = teclado.nextInt();

        switch (eleccion) {
            case 1:
                // Conexión a la base de datos
                conn = ConexionBaseDeDatos.ConexionBaseDeDatos(url, user, password);
                if (conn == null) {
                    ConexionBaseDeDatos.mensa("No se pudo establecer la conexión");
                    return;
                }
                System.out.println("Conexión exitosa a la base de datos.");
                break;
        

            case 4:
                ConexionBaseDeDatos.cerrarConexion(conn);
                ConexionBaseDeDatos.mensa("Conexión cerrada exitosamente!");
                break;
            default:
                ConexionBaseDeDatos.mensa("Elige una de las opciones que se dan.");
                break;
        }
    }
}