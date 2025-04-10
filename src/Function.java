import java.io.IOException;
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

    public static void tamañoCMD(){
        try {
            // Cambia columnas (ancho) y líneas (alto)
            String comando = "cmd /c mode con: cols=200 lines=60";
            Runtime.getRuntime().exec(comando);
            System.out.println("Tamaño de consola cambiado a 120x40");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mensa(String mensaje){
        System.out.println(mensaje);
    }

    public static boolean validarDNI(String swDNI) {
        if (swDNI == null || swDNI.length() != 9) {
            return false;
        }
    
        char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
    
        String numeroParte = swDNI.substring(0, 8);
        char letra = Character.toUpperCase(swDNI.charAt(8)); // por si ponen la letra en minúscula
    
        // Comprobar que los 8 primeros son dígitos
        if (!numeroParte.matches("\\d{8}")) {
            return false;
        }
    
        int num = Integer.parseInt(numeroParte);
        char letraCorrecta = letras[num % 23];
    
        return letra == letraCorrecta;
    }
    


    public static boolean validarTelf(String telefono){
        if (telefono == null || telefono.length() != 9) {
            return false;
        }
    
        // Comprobar que todos los caracteres son dígitos
        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarCorreo(String correo){
        if (correo == null || correo.isEmpty()) {
            return false;
        }

        int arroba = correo.indexOf('@');
        int punto = correo.lastIndexOf('.');

        // Debe tener una @, un punto después de la @ y algo antes y después del punto
        if (arroba > 0 && punto > arroba + 1 && punto < correo.length() - 1) {
            return true;
        }

        return false;
    }

    public static boolean validarSs(Integer swSs){
        if (swSs == null) {
            return false;
        }
        if (swSs < 100000000000L) {
            return true;
        }
        return false;
    }
/* 
    public static boolean validarFechaDev(String fechaDev, String fechaInicio, char letra){

        //Parseamos las fechas
        int devolucion=Integer.parseInt(fechaDev);
        int inicio=Integer.parseInt(fechaInicio);
        if (devolucion-inicio<=21 && letra=="S") {
            return true;
        }
        return false;
    }
*/
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

    //comprobacion id socios
    public static boolean comprobarIdSocio(Connection conn, String idSocio) {
        String sql = "SELECT COUNT(*) FROM socios WHERE id_socio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idSocio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el ID del socio: " + e.getMessage());
        }
        return false;
    }
}