import java.sql.*;
import java.util.Scanner;

public class TestConsultarTabla {
    public static int mostrarTabla(Connection conn){
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return 0;
        }
        int kont = 0;
        String sql = "SELECT * FROM socios order by id_socio";
        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Encabezados de la tabla
            System.out.printf("%-3s | %-10s | %-12s | %-10s | %-22s | %-10s | %-22s | %-15s | %-10s | %-10s%n",
            "Id", "Nombre", "Apellidos", "Dni", "Dirección", "Telefono", "Gmail", "Usuario", "Contraseña", "SS");
            System.out.println("========================================================================================================================================================");
        
        while (rs.next()) {
            String _cod = rs.getString("id_socio");
            String _Nombre = rs.getString("nombre");
            String _ape = rs.getString("apellido");
            String _dni = rs.getString("dni");
            String _direccion = rs.getString("direccion");
            String _tlfn = rs.getString("tlfn");
            String _correo = rs.getString("correo");
            String _usu = rs.getString("usuario");
            String _contraseña = rs.getString("contraseña");
            String _seguridad_social = rs.getString("seguridad_social");
        
            // Opcional: cortar campos largos si quieres
            if (_correo.length() > 22) _correo = _correo.substring(0, 22);
            if (_direccion.length() > 22) _direccion = _direccion.substring(0, 22);
        
            System.out.printf("%-3s | %-10s | %-12s | %-10s | %-22s | %-10s | %-22s | %-15s | %-10s | %-10s%n",
                _cod, _Nombre, _ape, _dni, _direccion, _tlfn, _correo, _usu, _contraseña, _seguridad_social);
                kont++;
        }
        
        } catch (SQLException e) {
            System.out.println("Problema al consultar: \n" + sql + "\n" + e.getErrorCode() + " " + e.getMessage());
        }
        return kont;
    }
    public static void paginarTabla(Connection conn, String tabla) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
        Scanner teclado = new Scanner(System.in);
        int offset = 0;
        int limit = 5;
        String sql = " ";

        switch (tabla.toLowerCase()) {
            case "socios":
                sql = "SELECT * FROM socios LIMIT ? OFFSET ?";
                break;
            case "libros":
                sql = "SELECT * FROM libros LIMIT ? OFFSET ?";
                break;
            case "autores":
                sql = "SELECT * FROM autores LIMIT ? OFFSET ?";
                break;
            case "prestamos":
                sql = "SELECT * FROM prestamos LIMIT ? OFFSET ?";
                break;
            case "penalizaciones":
                sql = "SELECT * FROM penalizaciones LIMIT ? OFFSET ?";
                break;
            default:
                System.out.println("Tabla no reconocida.");
                break;
        }

        boolean salir = false;
        while (!salir) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, limit);
                pstmt.setInt(2, offset);

                ResultSet rs = pstmt.executeQuery();

                System.out.println("\n--- Página " + (offset / limit + 1) + " ---");

                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();

                //Sirve para ver las columnas y su valor? Ns
                while (rs.next()) {
                    for (int i = 1; i <= columnas; i++) {
                        System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + " | ");
                    }
                    System.out.println();
                }

                rs.close();
                pstmt.close();

                System.out.println("\n[N] Siguiente | [P] Anterior | [S] Salir");
                String opcion = teclado.nextLine();

                switch (opcion.toUpperCase()) {
                    case "N":
                        offset += limit;
                        break;
                    case "P":
                        if (offset >= limit) offset -= limit;
                        break;
                    case "S":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar: " + e.getMessage());
            }
            break;
        }
        teclado.close();
    }
}