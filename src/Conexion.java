import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conexion{
    public static void main(String[] args) {
        String url = "jdbc:mysql://25.7.162.154:3306/bdbiblioteca";
        String user = "admin";
        String password = "1234";
        Connection conn = null;
        Scanner teclado = new Scanner(System.in);
        int eleccion=0;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (eleccion!=9) {
            Function.cls();
            
            System.out.println("\n                                                           " + fechaHoraActual.format(formato));
            System.out.println("*********************************************************************");
            System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
            System.out.println("*********************************************************************\n");
            System.out.println("       1-Obtener conexión                 4-Cerrar conexión");
            System.out.println("       5-Añadir  Usuario                  9-Salir de la aplicación\n");
            System.out.println("       6-Borrar Usuario                   10-Cambiar el color\n");
            System.out.println("       7-Mostrar socios paginando                                 \n");
            System.out.println("Elige la opción:");
            
    
            eleccion = teclado.nextInt();
    
            switch (eleccion) {
                case 1:
                    // Conexión a la base de datos
                    Function.mensa("Intentando conectar...");
                    conn = Function.ConexionBaseDeDatos(url, user, password);
                    if (conn == null) {
                        Function.mensa("No se pudo establecer la conexión");
                        System.out.println("Presiona Enter para continuar...");
                        teclado.nextLine(); // Limpia el \n después de nextInt
                        teclado.nextLine(); // Espera que el usuario pulse Enter
                        break;
                    }
                    System.out.println("Conexión exitosa a la base de datos.\n");
                    System.out.println("Presiona Enter para continuar...");
                    teclado.nextLine(); // Limpia el \n
                    teclado.nextLine(); // Espera Enter
                    break;
            
    
                case 4://cerrar conexion
                    Function.cerrarConexion(conn);
                    Function.mensa("Conexión cerrada exitosamente!\n");
                    break;

                    case 5://insert
                    Insert.InsertManual(conn);
                    Function.mensa("insert correcta");
                    break;
                    
                    case 6://borrar usuario
                    
                case 9://cerrar conexion y programa
                    Function.mensa("Gracias por usar la aplicación!!!! - © D4RK");
                    break;
    
                case 10:
                    Function.Color();
                    //TODO 
                    break;

                default:
                    Function.mensa("Elige una de las opciones que se dan.\n");
                    break;
            }
        }

        teclado.close();
    }
    public static int insertUsuarios(Connection conn,String _nombre,String _dni,String _apel){
        int nCambios=0;
        String swId;
        swId=""+(int)(Math.random()*1000);
        String sql="INSERT INTO TUSUARIOS(USUCOD,USUNOM,USUDNI,USUAPEL) VALUES ('"+swId+"','"+_nombre+"','"+_apel+"','"+_dni+"')";
        try {
            Statement stmt=conn.createStatement();
            nCambios=stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("\nProblema al crear tabla usuarios\n"+e.getErrorCode()+" "+e.getMessage());
        }
        return nCambios;
    }
    
}