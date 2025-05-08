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

    public static int mesesDeAlta(Connection conn){
        int meses = 0;
            
        String sql = "SELECT MONTHS_BETWEEN(sysdate, fecha_alta) FROM socios";
    
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                meses = (int) rs.getDouble(1);
            } 

            } catch (SQLException e) {
                    System.out.println("Error al contar tiempo de alta" + e.getMessage());
            }
        return meses;
    }
}

