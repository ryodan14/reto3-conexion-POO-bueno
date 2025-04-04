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
            System.out.println("                                          10-Cambiar el color\n");
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
    
                case 10:
                    Function.cls();
                    System.out.println("***********************");
                    System.out.println("**/     colores     /**");
                    System.out.println("***********************");
                    System.out.println("1-Verde");
                    System.out.println("2-Azul");
                    System.out.println("3-Rojo");
                    System.out.println("4-Amarillo");
                    System.out.println("5-Fondo azul");
                    System.out.println("6-Resetear");
                    eleccion = teclado.nextInt();
                    switch (eleccion) {
                        case 1:
                            System.out.println(VERDE);
                            break;

                        case 2:
                            System.out.println(AZUL);
                            break;

                        case 3:
                            System.out.println(ROJO);
                            break;

                        case 4:
                            System.out.println(AMARILLO);
                            break;

                        case 5:
                            System.out.println(FONDO_AZUL);
                            break;

                        case 6:
                            System.out.println(RESET);
                            break;
                    
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }

                default:
                    Function.mensa("Elige una de las opciones que se dan.\n");
                    break;
            }
        }

        teclado.close();
    }
}