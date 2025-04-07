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
    }
}
