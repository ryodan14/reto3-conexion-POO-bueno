import java.util.Scanner;
import java.sql.*;

public class Insert {

    public static void InsertManual(Connection conn) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        System.out.print("Seguridad Social: ");
        String seguridad_social = sc.nextLine();

        String sql = "INSERT INTO usuarios (nombre, apellido, direccion, telefono, correo, usuario, contraseña, seguridad_social) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setString(5, correo);
            stmt.setString(6, usuario);
            stmt.setString(7, contraseña);
            stmt.setString(8, seguridad_social);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro insertado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar: " + e.getMessage());
        }
    }
}
