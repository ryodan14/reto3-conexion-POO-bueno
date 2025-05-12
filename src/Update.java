import java.sql.*;
import java.util.*;

public class Update {
    public static void main(String[] args) {
        Connection conn = Function.ConexionBaseDeDatos("jdbc:mysql://127.0.0.1:3306/bdbiblioteca", "alumno", "alumno");
        if (conn == null) {
            System.out.println( "Fallo en la conexión");
            return;
        }
        System.out.println("Éxito en la conexión");

        Scanner sc = new Scanner(System.in);

        
        System.out.println("Introduce el ID del usuario a modificar: ");
        String id = sc.nextLine().trim();

        System.out.println("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine().trim();

        System.out.println("Nuevo apellido: ");
        String nuevoApellido = sc.nextLine().trim();

        System.out.println("Nuevo DNI:");
        String nuevoDni = sc.nextLine().trim();

        boolean exito = modificarUsuario(conn, id, nuevoNombre, nuevoApellido, nuevoDni);

        if (exito) {
            System.out.println("Usuario modificado correctamente.");
        } else {
            System.out.println("No se pudo modificar (ID no encontrado o error).");
        }

        sc.close();
        Function.cerrarConexion(conn);
    }

    public static boolean modificarUsuario(Connection conn, String id, String nombre, String apellido, String dni) {
        String sql = "UPDATE TUSUARIOS SET USUNOM = ?, USUAPEL = ?, USUDNI = ? WHERE USUCOD = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, dni);
            ps.setString(4, id);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
            return false;
        }
    }
}

