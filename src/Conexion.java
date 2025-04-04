import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conexion{
        // Códigos ANSI para colores
        public static final String RESET = "\u001B[0m";
        public static final String ROJO = "\u001B[31m";
        public static final String VERDE = "\u001B[32m";
        public static final String AMARILLO = "\u001B[33m";
        public static final String AZUL = "\u001B[34m";
        public static final String FONDO_AZUL = "\u001B[44m";
    public static void main(String[] args) {
        String url = "jdbc:mysql://25.7.162.154:3306/bdbiblioteca";
        String user = "admin";
        String password = "1234";
        Connection conn = null;
        Scanner teclado = new Scanner(System.in);
        int eleccion=0;

        while (eleccion!=9) {
            Function.cls();
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            System.out.println("\n                                                           " + fechaHoraActual.format(formato));
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
                    Function.mensa(ROJO+"Elige una de las opciones que se dan.\n"+RESET);
                    break;
            }
        }

        teclado.close();
    }
}