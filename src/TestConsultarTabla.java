import java.sql.*;

public class TestConsultarTabla {
    public static int mostrarTabla(Connection conn){
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
}
