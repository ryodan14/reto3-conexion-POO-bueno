import java.util.Scanner;
import java.sql.*;

public class Insert {

    public static void InsertManual(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        String id_socio = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String tlfn = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        System.out.print("Seguridad Social: ");
        String seguridad_social = sc.nextLine();

        String sql = "INSERT INTO socios (id_socio, dni, nombre, apellido, direccion, tlfn, correo, usuario, contraseña, seguridad_social) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_socio);
            pstmt.setString(2, dni);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido);
            pstmt.setString(5, direccion);
            pstmt.setString(6, tlfn);
            pstmt.setString(7, correo);
            pstmt.setString(8, usuario);
            pstmt.setString(9, contraseña);
            pstmt.setString(10, seguridad_social);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el usuario.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }
}
