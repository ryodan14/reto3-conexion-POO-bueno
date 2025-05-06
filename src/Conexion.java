import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conexion{
    public static void main(String[] args) throws InterruptedException{
        String url = "jdbc:mysql://bibliotecamuskiz.cb2cweukck18.eu-north-1.rds.amazonaws.com:3306/bdbiblioteca";
        String user = "admin";
        String password = "d4rk1234";
        Connection conn = null;
        Scanner teclado = new Scanner(System.in);
        int eleccion=0;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        

        while (eleccion!=10) {
            System.out.println("\n                                                           " + fechaHoraActual.format(formato));
            System.out.println("*********************************************************************");
            System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
            System.out.println("*********************************************************************\n");
            System.out.println("       1-Obtener conexión                 6-Cerrar conexión");
            System.out.println("       2-Consultar tablas                 7-Cambiar tamaño cmd");
            System.out.println("       3-Añadir/Borrar datos              8-Cambiar color");
            System.out.println("       4-Modificar tablas                 9-Limpiar pantalla");
            System.out.println("       5-Consultas especiales             10-Salir de la aplicación");
            System.out.println("                                          11-Creditos");
            System.out.println("\nElige la opción:");
            
    
            eleccion = teclado.nextInt();
    
            switch (eleccion) {

                // Conexión a la base de datos
                case 1:
                    Function.mensa("Intentando conectar...");
                    conn = Function.ConexionBaseDeDatos(url, user, password);
                    if (conn == null) {
                        Function.mensa("No se pudo establecer la conexión");
                        System.out.println("Presiona Enter para continuar...");
                        teclado.nextLine(); // Limpia el \n después de nextInt
                        teclado.nextLine(); // Espera que el usuario pulse Enter
                    }else{
                    System.out.println("Conexión exitosa a la base de datos.\n");
                    System.out.println("Presiona Enter para continuar...");
                    teclado.nextLine(); // Limpia el \n
                    teclado.nextLine(); // Espera Enter
                    }
                    break;

                //Consultar tabla
                case 2:
                    Function.mensa("Consultando tabla...");
                    
                    TestConsultarTabla.mostrarTabla(conn);
                    teclado.nextLine(); // Limpia el \n
                    teclado.nextLine(); // Espera Enter
                    break;
                    
                //insert
                case 3:
                    while (eleccion!=12) {
                        System.out.println("\n                                                           " + fechaHoraActual.format(formato));
                        System.out.println("*********************************************************************");
                        System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
                        System.out.println("*********************************************************************\n");
                        System.out.println("       1-Insertar Socios                    6-Borrar Socios");
                        System.out.println("       2-Insertar Libros                    7-Borrar Libros");
                        System.out.println("       3-Insertar Prestamos                 8-Borrar Prestamos");
                        System.out.println("       4-Insertar Autores                   9-Borrar Autores");
                        System.out.println("       5-Insertar Penalizaciones            10-Borrar Penalizaciones");
                        System.out.println("       11-prestamo entregado                12-Volver al inicio             ");
                        System.out.println("\nElige la opción:");
                        eleccion = teclado.nextInt();
                        switch (eleccion) {
                            case 1:
                                Insert.InsertSocios(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 2:
                                Insert.InsertLibros(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 3:
                                Insert.InsertPrestamos(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 4:
                                Insert.InsertAutores(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 5: 
                                Insert.InsertPenalizaciones(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 6:
                                Delete.DeleteSocios(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 7:
                                Delete.DeleteLibros(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;  
                            case 8:
                                Delete.DeletePrestamos(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 9:
                                Delete.DeleteAutores(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 10:
                                Delete.DeletePenalizaciones(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 11:
                                Insert.PrestamoEntregado(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 12:
                                Function.mensa("Saliendo de la opción de inserción...");
                                break;
                        
                            default:
                                break;
                        }
                    }
                    break;

                //Modificar Tablas
                case 4:
                while (eleccion!=12) {
                    System.out.println("\n                                                           " + fechaHoraActual.format(formato));
                    System.out.println("*********************************************************************");
                    System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
                    System.out.println("*********************************************************************\n");
                    System.out.println("       1-Modificar Socios                   2-Modificar Libros");
                    System.out.println("       3-Modificar Prestamos                4-Modificar Autores");
                    System.out.println("       5-Volver al inicio                    ");
                    System.out.println("\nElige la opción:");
                    eleccion = teclado.nextInt();
                    switch (eleccion) {
                        case 1:
                            Insert.InsertSocios(conn);
                            System.out.println("Presiona Enter para continuar...");
                            teclado.nextLine(); // Limpia el \n después de nextInt
                            teclado.nextLine(); // Espera que el usuario pulse Enter
                            break;
                        case 2:
                            Insert.InsertLibros(conn);
                            System.out.println("Presiona Enter para continuar...");
                            teclado.nextLine(); // Limpia el \n después de nextInt
                            teclado.nextLine(); // Espera que el usuario pulse Enter
                            break;
                        case 3:
                            Insert.InsertPrestamos(conn);
                            System.out.println("Presiona Enter para continuar...");
                            teclado.nextLine(); // Limpia el \n después de nextInt
                            teclado.nextLine(); // Espera que el usuario pulse Enter
                            break;
                        case 4:
                            Insert.InsertAutores(conn);
                            System.out.println("Presiona Enter para continuar...");
                            teclado.nextLine(); // Limpia el \n después de nextInt
                            teclado.nextLine(); // Espera que el usuario pulse Enter
                            break;
                        case 5: 
                            Insert.InsertPenalizaciones(conn);
                            System.out.println("Presiona Enter para continuar...");
                            teclado.nextLine(); // Limpia el \n después de nextInt
                            teclado.nextLine(); // Espera que el usuario pulse Enter
                            break;
                    }
                }
                //Mostrar socios paginando
                case 5:

                    break;

                //Cerrar conexion
                case 6:
                    Function.mensa("Cerrando la conexión...\n");
                    Thread.sleep(2000);
                    Function.cerrarConexion(conn);
                    Function.mensa("Conexión cerrada exitosamente!\n");
                    teclado.nextLine(); // Espera Enter
                    break;

                //Cambiar tamaño CMD
                case 7:

                    break;

                //Cambiar color
                case 8:
                    Function.Color();
                    teclado.nextLine(); // Limpia el \n después de nextInt
                    teclado.nextLine(); // Espera que el usuario pulse Enter
                    break;
    
                //limpiar CMD
                case 9:
                    Function.cls();
                    break;

                //Cerrar conexion y programa
                case 10:
                    Function.mostrarTextoLento("Gracias por usar la aplicación!!!! - © D4RK");
                    teclado.nextLine(); // Limpia el \n después de nextInt
                    teclado.nextLine(); // Espera que el usuario pulse Enter
                    break;

                //Creditos
                case 11:
                    PantallaCreditos.main(args);
                    break;
                
                //Por si el usuario no mete la opción correcta
                default:
                    Function.mensa("Elige una de las opciones que se dan.\n");
                    teclado.nextLine(); // Limpia el \n después de nextInt
                    teclado.nextLine(); // Espera que el usuario pulse Enter
                    break;
            }
        }
        teclado.close();
    }
}

