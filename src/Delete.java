import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    public static void DeleteManual(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("¿Cómo quieres borrar el usuario?");
        System.out.println("1. Por ID");
        System.out.println("2. Por DNI");
        System.out.println("3. Por nombre y apellido");
        System.out.print("Elige opción: ");
        String opcion = sc.nextLine();

        String sql = "";
        try {
            PreparedStatement pstmt = null;

            switch (opcion) {
                case "1":
                    System.out.print("Introduce el ID del socio: ");
                    String id = sc.nextLine();
                    sql = "DELETE FROM socios WHERE id_socio = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    break;

                case "2":
                    System.out.print("Introduce el DNI del socio: ");
                    String dni = sc.nextLine();
                    sql = "DELETE FROM socios WHERE dni = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, dni);
                    break;

                case "3":
                    System.out.print("Introduce el nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Introduce el apellido: ");
                    String apellido = sc.nextLine();
                    sql = "DELETE FROM socios WHERE nombre = ? AND apellido = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombre);
                    pstmt.setString(2, apellido);
                    break;

                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario borrado correctamente.");
            } else {
                System.out.println("No se encontró ningún usuario con ese dato.");
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error al borrar el usuario: " + e.getMessage());
        }
    }
}
