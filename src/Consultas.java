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

    /*public static boolean prestamosActivos(Connection conn){
        String sql = "SELECT COUNT(*) FROM socios WHERE seguridad_social IS NOT NULL";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                cont = rs.getInt(1);  // Obtener el valor del COUNT(*)
            }

        } catch (SQLException e) {
                System.out.println("Error al contar empleados: " + e.getMessage());
        }
    }

    public static int cantPrestamosActivos(Connection conn){
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
    }*/
}

