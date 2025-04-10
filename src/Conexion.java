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
            System.out.println("\n                                                           " + fechaHoraActual.format(formato));
            System.out.println("*********************************************************************");
            System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
            System.out.println("*********************************************************************\n");
            System.out.println("       1-Obtener conexión                 4-Cerrar conexión");
            System.out.println("       2-Consultar la tabla               3-Cambiar tamaño cmd");
            System.out.println("       5-Añadir/Borrar datos              9-Salir de la aplicación");
            System.out.println("       6-Borrar Usuario                   10-Cambiar el color");
            System.out.println("       7-Mostrar socios paginando                                 ");
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
                    }
                    System.out.println("Conexión exitosa a la base de datos.\n");
                    System.out.println("Presiona Enter para continuar...");
                    teclado.nextLine(); // Limpia el \n
                    teclado.nextLine(); // Espera Enter
                    break;

                case 2:
                    Function.mensa("Consultando tabla...");
                    
                    TestConsultarTabla.mostrarTabla(conn);
                    teclado.nextLine(); // Limpia el \n
                    teclado.nextLine(); // Espera Enter
                    break;
            
                case 3:
                    Function.tamañoCMD();
                    break;
    
                case 4://cerrar conexion
                    Function.cerrarConexion(conn);
                    Function.mensa("Conexión cerrada exitosamente!\n");
                    break;

                case 5://insert
                    while (eleccion!=11) {
                        System.out.println("\n                                                           " + fechaHoraActual.format(formato));
                        System.out.println("*********************************************************************");
                        System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
                        System.out.println("*********************************************************************\n");
                        System.out.println("       1-Insertar Socios                    6-Borrar Socios");
                        System.out.println("       2-Insertar Libros                    7-Borrar Libros");
                        System.out.println("       3-Insertar Prestamos                 8-Borrar Prestamos");
                        System.out.println("       4-Insertar Autores                   9-Borrar Autores");
                        System.out.println("       5-Insertar Penalizaciones            10-Borrar Penalizaciones");
                        System.out.println("       11-Volver al inicio             ");
                        System.out.println("Elige la opción:");
                        eleccion = teclado.nextInt();
                        switch (eleccion) {
                            case 1:
                                Insert.InsertSocios(conn);
                                Function.mensa("Usuario Insertado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 2:
                                Insert.InsertLibros(conn);
                                Function.mensa("Libro Insertado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 3:
                                Insert.InsertPrestamos(conn);
                                Function.mensa("Prestamo Insertado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 4:
                                Insert.InsertAutores(conn);
                                Function.mensa("Autor Insertado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 5: 
                                Insert.InsertPenalizaciones(conn);
                                Function.mensa("Penalizacion Insertada correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 6:
                                Delete.DeleteSocios(conn);
                                Function.mensa("Socio Borrado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 7:
                                Delete.DeleteLibros(conn);
                                Function.mensa("Libro Borrado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;  
                            case 8:
                                Delete.DeletePrestamos(conn);
                                Function.mensa("Prestamo Borrado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 9:
                                Delete.DeleteAutores(conn);
                                Function.mensa("Autor Borrado correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 10:
                                Delete.DeletePenalizaciones(conn);
                                Function.mensa("Penalizacion Borrada correctamente");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 11:
                                Function.mensa("Saliendo de la opción de inserción...");
                                break;
                        
                            default:
                                break;
                        };}
                
                    
                case 9://cerrar conexion y programa
                    Function.mensa("Gracias por usar la aplicación!!!! - © D4RK");
                    break;
    
                case 10:
                    Function.Color();
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

