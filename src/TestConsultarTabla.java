import java.sql.*;

public class TestConsultarTabla {
    public static void main(String[] args) {
        String url = "jdbc:mysql://25.7.162.154:3306/bdbiblioteca";
        String user = "admin";
        String password = "1234";
        Connection conn = null;

        // Conexión a la base de datos
        conn = Function.ConexionBaseDeDatos(url,user,password);
        if (conn== null){
            System.out.println("Fallo en la conexion");
            return;
        }
        System.out.println("Exito en la conexion");
        System.out.println("Se han visualizado " + mostrarTabla(conn) + " registros");
        Function.cerrarConexion(conn);
    }

    public static int mostrarTabla(Connection conn){
        int kont = 0;
        String sql="SELECT * FROM socios";
        System.out.println(sql);
        try{
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String _cod = rs.getString("id_socio");
                String _Nombre = rs.getString("nombre");
                String _ape = rs.getString("apellido");
                String _dni = rs.getString("dni");
                String _direccion = rs.getString("direccion");
                String _tlfn = rs.getString("tlfn");
                String _correo = rs.getString("correo");
                String _usu = rs.getString("usu");
                String _contraseña = rs.getString("contraseña");
                String _seguridad_social = rs.getString("seguridad_social");

                System.out.println(_cod + " | " + _Nombre + " | " + _ape + " | " + _dni+" | "+_direccion+" | "+_tlfn+" | "+_correo+" | "+_usu+" | "+_contraseña+" | "+_seguridad_social);
                kont+=1;
            }
        }catch (SQLException e){
            System.out.println("Problema al consultar: "
                +"\n"+sql +"\n"+e.getErrorCode() + " " + e.getMessage());
        }
        return kont;
    }
}