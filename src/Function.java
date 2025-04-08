import java.sql.*;
import java.util.Scanner;

public class Function {
    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String FONDO_AZUL = "\u001B[44m";

    public static void Color(){
        Scanner escanea = new Scanner(System.in);
        int decision=0;
        cls();
        System.out.println("***********************");
        System.out.println("**/     colores     /**");
        System.out.println("***********************");
        System.out.println("1-Verde");
        System.out.println("2-Azul");
        System.out.println("3-Rojo");
        System.out.println("4-Amarillo");
        System.out.println("5-Fondo azul");
        System.out.println("6-Resetear");
        decision = escanea.nextInt();
        switch (decision) {
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
        escanea.close();
    }

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mensa(String mensaje){
        System.out.println(mensaje);
    }

    public static Connection ConexionBaseDeDatos(String url, String user, String password){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC.");
            return null;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
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