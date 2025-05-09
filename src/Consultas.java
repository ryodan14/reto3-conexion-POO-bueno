import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void tiempoTrabajado(){
        
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
        String sql = "SELECT nombre, apellido, TIMESTAMPDIFF(MONTH, fecha_alta, CURDATE()) AS meses_alta FROM socios";
    
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
    
            System.out.printf("%-20s %-25s %-10s%n", "Nombre", "Apellido", "Meses Alta");
            System.out.println("--------------------------------------------------------------");
    
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellido");
                int mesesAlta = rs.getInt("meses_alta");
    
                System.out.printf("%-20s %-25s %-10d%n", nombre, apellidos, mesesAlta);
            }
    
        } catch (SQLException e) {
            System.out.println("Error al obtener meses de alta por socio: " + e.getMessage());
        }
    }
}

