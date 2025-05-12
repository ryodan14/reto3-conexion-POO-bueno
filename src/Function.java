import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Function {
    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String FONDO_AZUL = "\u001B[44m";

    public static final Scanner sc= new Scanner(System.in);

    public static void Color(){
        int decision=0;
        cls();
        System.out.println("***********************");
        System.out.println("**/     COLORES     /**");
        System.out.println("***********************");
        System.out.println("1-Verde");
        System.out.println("2-Azul");
        System.out.println("3-Rojo");
        System.out.println("4-Amarillo");
        System.out.println("5-Fondo azul");
        System.out.println("6-Resetear");
        decision = sc.nextInt();
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
        System.out.println("Color cambiado con exito!!!!");
    }

    public static void cls() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void mensa(String mensaje){
        System.out.println(mensaje);
    }

    public static void mostrarTextoLento(String texto) throws InterruptedException {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            Thread.sleep(30); // Tiempo entre letras (ms)
        }
        System.out.println(); // Nueva línea después del texto
        Thread.sleep(300); // Pausa entre líneas
    }

        //mmetodo para validar DNI
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
    

        //METODO VALIDAR TELEFONO
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
        //METODO VALIDAR CORREO

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

      //METODO PARA VALIDAR EL NÚMERO DE LA SEGURIDAD SOCIAL
        public static boolean validarSs(Long swSs) {
        if (swSs == null) return true; // Es opcional
        return swSs >= 100000000000L && swSs <= 999999999999L; // Exactamente 12 dígitos
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
    public static boolean comprobarIdSocio(Connection conn, int idSocio) {
        String sql = "SELECT COUNT(*) FROM socios WHERE id_socio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSocio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el ID del socio: " + e.getMessage());
        }
        return false;
    }

    //comprobacion dni socios
    public static boolean dniYaExiste(Connection conn, String dni) {
        String sql = "SELECT COUNT(*) FROM socios WHERE dni = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                // Devuelve true si existe al menos un registro con el DNI proporcionado
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar el DNI del socio: " + e.getMessage());
        }
        return false;
    }
    
    //comprobacion id libros
    public static boolean comprobarIdLibro(Connection conn, int idLibro) {
        String sql = "SELECT COUNT(*) FROM libros WHERE id_libro = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idLibro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el ID del libro: " + e.getMessage());
        }
        return false;
    }

    // comprobación título libro
    public static boolean comprobarTituloLibro(Connection conn, String tituloLibro) {
        String sql = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tituloLibro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe sin importar mayúsculas
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el título del libro: " + e.getMessage());
        }
        return false;
    }
    

    //comprobacion isbn libro
    public static boolean comprobarISBNLibro(Connection conn, String ISBNLibro) {
        String sql = "SELECT COUNT(*) FROM libros WHERE isbn = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ISBNLibro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el ISBN del libro: " + e.getMessage());
        }
        return false;
    }

    //comprobacion codigo prestamo
    public static boolean comprobarIdPrestamo(Connection conn, int idPrestamo) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE codigo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPrestamo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el codigo del prestamo: " + e.getMessage());
        }
        return false;
    }

    //comprobacion de que el socio no lleva 3 prestamos
    public static boolean comprobarIdSocioPrestamo(Connection conn, int idSocioPrestamo) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE socio = ? and entregado = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSocioPrestamo);
            pstmt.setString(2, "N");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) >= 3; // Devuelve true si existe al menos tres registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el codigo del socio: " + e.getMessage());
        }
        return false;
    }

    
    //comprobacion codigo prestamo
    public static boolean comprobarIdPenalizado(Connection conn, int idPenalizado) {
        String sql = "SELECT COUNT(*) FROM penalizaciones WHERE codigo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPenalizado);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el codigo del prestamo: " + e.getMessage());
        }
        return false;
    }

            //update de que ha entregado el libro
        public static void marcarPrestamoComoEntregado(Connection conn, String codigoPrestamo) {
    String sql = "UPDATE prestamos SET entregado = 'S', fecha_devolucion = ? WHERE codigo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String fechaHoy = LocalDate.now().toString(); // o la fecha que quieras registrar
            pstmt.setString(1, fechaHoy);
            pstmt.setString(2, codigoPrestamo);

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Préstamo marcado como entregado.");
            } else {
                System.out.println("No se encontró el préstamo con ese código.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el préstamo: " + e.getMessage());
        }
    }


    //comprobacion id autores
    public static boolean comprobarIdAutor(Connection conn, int idAutor) {
        String sql = "SELECT COUNT(*) FROM autores WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAutor);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
            } 
        } catch (SQLException e) {
            System.out.println("Error al comprobar el ID del Autor: " + e.getMessage());
        }
        return false;
    }

        // comprobar nombre y apellidos del autor
        public static boolean comprobarNombreAutor(Connection conn, String nombre, String apellido1, String apellido2) {
            String sql = "SELECT COUNT(*) FROM autores WHERE nombre = ? AND apellido1 = ? AND apellido2 = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, apellido1);
                pstmt.setString(3, apellido2);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Devuelve true si existe al menos un registro
                }
            } catch (SQLException e) {
                System.out.println("Error al comprobar el nombre del autor: " + e.getMessage());
            }
            return false;
        }
        // Comprobación de penalización para socios
        public static void comprobarPenalizacion(Connection conn) {
            if (conn == null) {
                System.out.println("No hay conexión con la base de datos.");
                return;
            }

            String sql = "SELECT codigo, socio, fecha_inicio FROM prestamos WHERE entregado = 'N'";

            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String codigo = rs.getString("codigo");
                    String socio = rs.getString("socio");
                    LocalDate fechaInicio = LocalDate.parse(rs.getString("fecha_inicio"));
                    LocalDate hoy = LocalDate.now();

                    if (ChronoUnit.DAYS.between(fechaInicio, hoy) > 14) {
                        // Penaliza al socio si tiene el préstamo sin devolver por más de 14 días
                        penalizarSocio(conn, socio);
                        System.out.println("Socio " + socio + " penalizado por el préstamo " + codigo);
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error al comprobar penalizaciones: " + e.getMessage());
            }
        }

    
    //penealizacion socios
    public static void penalizarSocio(Connection conn, String idSocio) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
        
        String sql = "UPDATE penalizaciones SET penalizado = 'S' WHERE id_socio = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idSocio);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Socio penalizado con éxito.");
            } else {
                System.out.println("No se encontró el socio con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al penalizar al socio: " + e.getMessage());
        }
    }

    public static String mayusculas(String palabra) {
        palabra=palabra.toUpperCase();
        return palabra;
    }
    public static int obtenerIdLibroPorTitulo (Connection conn, String titulo) {
        String sql = "SELECT id_libro FROM libros WHERE UPPER(titulo) = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo.toUpperCase()); // Asegura coincidencia sin importar mayúsculas
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_libro");
            } else {
                return 0; // No se encontró ningún libro con ese título
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del libro: " + e.getMessage());
            return 0; // Error en la consulta
        }
    }

    public static int obtenerEjemplares(Connection conn, int id_libro) {
        String sql = "SELECT ejemplares FROM libros WHERE id_libro = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_libro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ejemplares");
            } else {
                return -1; // No existe el libro
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los ejemplares del libro: " + e.getMessage());
            return -2; // Error en la consulta
        }
    }
    public static void actualizarEjemplares(Connection conn, int id_libro, int ejemplares) {
        String sql = "UPDATE libros SET ejemplares = ? WHERE id_libro = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ejemplares);
            pstmt.setInt(2, id_libro);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar los ejemplares del libro: " + e.getMessage());
        }
    }

    public static int obtenerIdLibroPorPrestamo (Connection conn, int codigo) {
        String sql = "SELECT id_libro FROM libros WHERE id_libro in(select id_libro from prestamos where codigo = ?) ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_libro");
            } else {
                return 0; // No se encontró ningún libro con ese título
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del libro: " + e.getMessage());
            return 0; // Error en la consulta
        }
    }
    
    public static int validarnumero(Scanner teclado) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        while (true) {
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine(); // limpia el salto de línea
                return numero;
            } else {
                System.out.println("Por favor, introduce un número válido:");
                scanner.nextLine(); // limpia el texto incorrecto
            }
        }
    }

    //gracias Ander por el metodo:))
}