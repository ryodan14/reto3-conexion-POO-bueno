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
        int eleccion1=0;
        int eleccion2=0;
        int eleccion3=0;
        int principal=0;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        

        while (principal!=9) {
            System.out.println("*********************************************************************");
            System.out.println("***/                  BIBLIOTECA MUSKIZ               |"+ fechaHoraActual.format(formato)+"|***");
            System.out.println("*********************************************************************\n");
            System.out.println("       1-Obtener conexión                 6-Cerrar conexión");
            System.out.println("       2-Consultar tablas                 7-Cambiar color");
            System.out.println("       3-Añadir/Borrar datos              8-Limpiar pantalla");
            System.out.println("       4-Modificar tablas                 9-Salir de la aplicación");
            System.out.println("       5-Consultas especiales             10-Creditos");
            System.out.println("\nElige la opción:");
            
            principal = Function.validarnumero(teclado);

            try {
    
                switch (principal) {

                    // Conexión a la base de datos
                    case 1:
                        Function.mensa("Intentando conectar...");
                        conn = Function.ConexionBaseDeDatos(url, user, password);
                        if (conn == null) {
                            Function.mensa("No se pudo establecer la conexión");
                        }else{
                        System.out.println("Conexión exitosa a la base de datos.\n");
                        }
                        break;

                    //Consultar tabla
                    case 2:
                        Function.mensa("Consultando tabla...");
                        eleccion=0;
                        while (eleccion!=6) {
                            System.out.println("\n                                                           " + fechaHoraActual.format(formato));
                            System.out.println("*********************************************************************");
                            System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
                            System.out.println("*********************************************************************\n");
                            System.out.println("       1-Tabla Socios                    4-Tabla Autores ");
                            System.out.println("       2-Tabla Libros                    5-Tabla Penalizaciones");
                            System.out.println("       3-Tabla Prestamos                 6-Volver al inicio");
                            System.out.println("\nElige la opción:");
                            eleccion = Function.validarnumero(teclado);
                            switch (eleccion) {
                                case 1:
                                    TestConsultarTabla testConsultarTabla = new TestConsultarTabla();
                                    testConsultarTabla.consultarTablaSocios(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 2:
                                    TestConsultarTabla testConsultarTabla2 = new TestConsultarTabla();
                                    testConsultarTabla2.consultarTablaLibros(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 3:
                                    TestConsultarTabla testConsultarTabla3 = new TestConsultarTabla();
                                    testConsultarTabla3.consultarTablaPrestamos(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 4:
                                    TestConsultarTabla testConsultarTabla4 = new TestConsultarTabla();
                                    testConsultarTabla4.consultarTablaAutores(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 5:
                                    TestConsultarTabla testConsultarTabla5 = new TestConsultarTabla();
                                    testConsultarTabla5.consultarTablaPenalizaciones(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 6:
                                    Function.mensa("Saliendo de la consulta...");
                                    Function.mensa("Presiona Enter para continuar...");
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                default:
                                    Function.mensa("Elige una de las opciones que se dan.\n");
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                            }
                        }
                        break;
                    //insert
                    case 3:
                        eleccion1=0;
                        while (eleccion1!=12) {
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
                            eleccion1 = Function.validarnumero(teclado);
                            switch (eleccion1) {
                                case 1:
                                    Insert.InsertSocios(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 2:
                                    Insert.InsertLibros(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 3:
                                    Insert.InsertPrestamos(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 4:
                                    Insert.InsertAutores(conn);
                                    Function.mensa("Presiona Enter para continuar...");
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 5: 
                                    Insert.InsertPenalizaciones(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 6:
                                    Delete.DeleteSocios(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 7:
                                    Delete.DeleteLibros(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;  
                                case 8:
                                    Delete.DeletePrestamos(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 9:
                                    Delete.DeleteAutores(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 10:
                                    Delete.DeletePenalizaciones(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 11:
                                    Insert.PrestamoEntregado(conn);
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                                case 12:
                                    Function.mensa("Saliendo de la opción de inserción...");
                                    break;
                            
                                default:
                                    System.out.println("Elige una de las opciones que se dan.\n");
                                    teclado.nextLine(); // Limpia el \n después de nextInt
                                    break;
                            }
                        }
                        break;

                    //Modificar Tablas
                    case 4:
                    eleccion2=0;
                    while (eleccion2!=5) {
                        System.out.println("\n                                                           " + fechaHoraActual.format(formato));
                        System.out.println("*********************************************************************");
                        System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
                        System.out.println("*********************************************************************\n");
                        System.out.println("       1-Modificar Socios                   2-Modificar Libros");
                        System.out.println("       3-Modificar Prestamos                4-Modificar Autores");
                        System.out.println("       5-Volver al inicio                    ");
                        System.out.println("\nElige la opción:");
                        eleccion2 = Function.validarnumero(teclado);
                        switch (eleccion2) {
                            case 1:
                                Update.modificarSocios(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                Thread.sleep(2000);
                                break;
                            case 2:
                                Update.modificarLibros(conn);
                                System.out.println("Presiona Enter para continuar...");
                                Thread.sleep(5000);
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 3:
                                Update.modificarPrestamos(conn);
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
                                Function.mensa("Saliendo de la modificacion");
                                break;
                            default: 
                                System.out.println("Elige una de las opciones que se dan.\n");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                break;
                        }
                        
                    }
                    break;
                    //Consultas especiales 
                    case 5:
                    eleccion3=0;
                    while (eleccion3!=7) {
                        System.out.println("\n                                                           " + fechaHoraActual.format(formato));
                        System.out.println("*********************************************************************");
                        System.out.println("***/                    BIBLIOTECA MUSKIZ                        /***");
                        System.out.println("*********************************************************************\n");
                        System.out.println("       1-Contar empleados                   2-Prestamos activos");
                        System.out.println("       3-Meses dados de alta                4-Min/Max/Avg paginas");
                        System.out.println("       5-buscar socios por nombre??         6-Penalizaciones por tipo ");
                        System.out.println("       7-Volver al inicio                                            ");
                        System.out.println("\nElige la opción:");
                        eleccion3 = Function.validarnumero(teclado);
                        switch (eleccion3) {
                            case 1:
                                System.out.println("Contamos con " + Consultas.contarEmpleados(conn) + " empleados en nuestra biblioteca.");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                break;
                            case 2:
                                Consultas.hayPrestamosActivos(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 3:
                                Consultas.mesesDeAlta(conn); 
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 4: 
                                Consultas.calcLibros(conn);
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            case 5: 
                                Consultas.buscarNombresPorLetra(conn);
                                break;
                            case 6: 
                                Consultas.penalPorTipo(conn);
                                break;
                            case 7:
                                Function.mensa("Saliendo de la consulta especial...");
                                break;
                            default:
                                System.out.println("Elige una de las opciones que se dan.\n");
                                System.out.println("Presiona Enter para continuar...");
                                teclado.nextLine(); // Limpia el \n después de nextInt
                                teclado.nextLine(); // Espera que el usuario pulse Enter
                                break;
                            
                        }
                        
                    }
                    break;
                    //Cerrar conexion
                    case 6:
                        Function.mensa("Cerrando la conexión...\n");
                        Function.cerrarConexion(conn);
                        Function.mensa("Conexión cerrada exitosamente!\n");
                        teclado.nextLine(); // Espera Enter
                        break;

                    //Cambiar color
                    case 7:
                        Function.Color();
                        teclado.nextLine(); // Espera que el usuario pulse Enter

                        break;
        
                    //limpiar CMD
                    case 8:
                        Function.cls();
                        break;

                    //Cerrar conexion y programa
                    case 9:
                        Function.mostrarTextoLento("Gracias por usar la aplicación!!!! - © D4RK");
                        break;

                    //Creditos
                    case 10:
                        PantallaCreditos.main(args);
                        break;
                    
                    //Por si el usuario no mete la opción correcta
                    default:
                        Function.mensa("Elige una de las opciones que se dan.\n");
                        break;
                }
            
    } catch (InputMismatchException ime) {
        System.out.println("Por favor, introduce un número válido.");
        teclado.nextLine(); // Limpiar buffer
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        teclado.nextLine(); // Evitar bucle infinito si se produce excepción
    } finally {
    System.out.println("Presiona Enter para continuar...");
    if (teclado.hasNextLine()) {
        teclado.nextLine();
    }
    }
    }
    }
}





