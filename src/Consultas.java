import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Consultas{
    public static int contarEmpleados(Connection conn){
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return 0;
        }
        int cont = 0;
        String sql = "SELECT COUNT(*) FROM socios WHERE seguridad_social IS NOT NULL";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                cont = rs.getInt(1);  // Obtener el valor del COUNT(*)
            }

        } catch (SQLException e) {
                System.out.println("Error al contar empleados: " + e.getMessage());
            }
        return cont;
    }


    public static void hayPrestamosActivos(Connection conn){
        int cont = 0;
        String sql = "SELECT COUNT(*) FROM prestamos WHERE entregado = 'N'";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                cont = rs.getInt(1);  // Obtener el valor del COUNT(*)
            }
            if (cont == 0) System.out.println("No hay prestamos activos, todos están entregados.");
                else System.out.println("Hay " + cont + " prestamos activos.");

        } catch (SQLException e) {
                System.out.println("Error al verificar préstamos activos" + e.getMessage());
        }
    }

    public static void mesesDeAlta(Connection conn) {
    String sql = "SELECT nombre, apellido, " +
                "TIMESTAMPDIFF(MONTH, fecha_alta, CURDATE()) AS meses_alta, " +
                "TIMESTAMPDIFF(DAY, fecha_alta, CURDATE()) AS dias_alta " +
                "FROM socios";

    try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

        System.out.printf("%-20s %-25s %-15s%n", "Nombre", "Apellido", "Tiempo de Alta");
        System.out.println("------------------------------------------------------------------");

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellido");
            int mesesAlta = rs.getInt("meses_alta");
            int diasAlta = rs.getInt("dias_alta");

            String tiempoAlta = (mesesAlta == 0) ? diasAlta + " días" : mesesAlta + " meses";

            System.out.printf("%-20s %-25s %-15s%n", nombre, apellidos, tiempoAlta);
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener meses de alta por socio: " + e.getMessage());
    }
}


    /*Metodo para calcular libro con mas paginas, libro con menos paginas y media de paginas de todos los libros*/
    public static void calcLibros(Connection conn){
        String tituloMax = "";
        String tituloMin = "";
        int max = 0;
        int min = 0;
        double media = 0;

        // Libro con más páginas
        String sqlMax = "SELECT titulo, numPags\n" + //
                        "FROM libros\n" + //
                        "WHERE numPags = (SELECT MAX(numPags) FROM libros);";
        // Libro con menos páginas
        String sqlMin = "SELECT titulo, numPags\n" + //
                        "FROM libros\n" + //
                        "WHERE numPags = (SELECT MIN(numPags) FROM libros);";
        // Promedio de páginas
        String sqlAvg = "SELECT AVG(numPags) AS avgPags FROM libros";

        try (Statement stmt = conn.createStatement(); ResultSet rsMax = stmt.executeQuery(sqlMax)) {
            if (rsMax.next()) {
                tituloMax = rsMax.getString("titulo");
                max = rsMax.getInt("numPags");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el libro con más páginas: " + e.getMessage());
        }

        try (Statement stmt = conn.createStatement(); ResultSet rsMin = stmt.executeQuery(sqlMin)) {
            if (rsMin.next()) {
                tituloMin = rsMin.getString("titulo");
                min = rsMin.getInt("numPags");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el libro con menos páginas: " + e.getMessage());
        }

        try (Statement stmt = conn.createStatement(); ResultSet rsAvg = stmt.executeQuery(sqlAvg)) {
            if (rsAvg.next()) {
                media = rsAvg.getDouble("avgPags");
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular la media de páginas: " + e.getMessage());
        }

        // Mostrar resultados
        System.out.println("Libro con más páginas: " + tituloMax + " (" + max + " páginas)");
        System.out.println("Libro con menos páginas: " + tituloMin + " (" + min + " páginas)");
        System.out.printf("Promedio de páginas: %.2f%n", media);
    }

    public static void penalPorTipo(Connection conn) {
        String sql = "SELECT tipo, COUNT(*) AS total FROM penalizaciones GROUP BY tipo";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                int total = rs.getInt("total");
                System.out.println("Hay " + total + " penalizaciones del tipo " + tipo);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las penalizaciones por tipo: " + e.getMessage());
        }
    }

    public static void buscarNombresPorLetra(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa una letra o cadena: ");
        String cadena = scanner.nextLine().toUpperCase().trim();  // Convertir a mayúsculas y eliminar espacios extra

        // Validar que la entrada no esté vacía y no contenga números o espacios adicionales
        if (cadena.isEmpty() || !cadena.matches("[a-zA-Z]+")) {
            System.out.println("Error: Debes ingresar solo letras.");
            scanner.close();
            return;
        }

        String sql = "SELECT nombre, apellido FROM socios WHERE nombre LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + cadena + "%");  // Buscar por la cadena introducida

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Socios cuyo nombre contenga '" + cadena + "':");

                boolean encontrado = false;
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");

                    System.out.println(nombre + " " + apellido);
                    encontrado = true;
                }

                if (!encontrado) {
                    System.out.println("No se encontraron socios con esa cadena.");
                }

            }

        } catch (SQLException e) {
            System.out.println("Error al buscar socios: " + e.getMessage());
            e.printStackTrace();  // Para depurar el error con más detalle
        }
        scanner.close();
    }
}


