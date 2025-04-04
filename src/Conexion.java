import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conexion{
    public static void main(String[] args) {
        String url = "jdbc:mysql://25.7.162.154:3306/bdbiblioteca";
        String user = "admin";
        String password = "1234";
        Connection conn = null;
        Scanner teclado = new Scanner(System.in);
        int eleccion=0;

        while (eleccion!=9) {
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            System.out.println("                                                          " + fechaHoraActual.format(formato));
            System.out.println("*********************************************************************");
            System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
            System.out.println("*********************************************************************\n");
            System.out.println("       1-Obtener conexión                 4-Cerrar conexión");
            System.out.println("                                          9-Salir de la aplicación\n");
            System.out.println("Elige la opción:");
    
    
            eleccion = teclado.nextInt();
    
            switch (eleccion) {
                case 1:
                    // Conexión a la base de datos
                    Function.mensa("Intentando conectar...");
                    conn = Function.ConexionBaseDeDatos(url, user, password);
                    if (conn == null) {
                        Function.mensa("No se pudo establecer la conexión");
                        break;
                    }
                    System.out.println("Conexión exitosa a la base de datos.\n");
                    break;
            
    
                case 4:
                    Function.cerrarConexion(conn);
                    Function.mensa("Conexión cerrada exitosamente!\n");
                    break;
    
                case 9:
                    Function.mensa("Gracias por usar la aplicación!!!! - © D4RK");
                    break;
    
                default:
                    Function.mensa("Elige una de las opciones que se dan.\n");
                    break;
            }
        }

        teclado.close();
    }
}