import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Update {
    public static void modificarSocios(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
        Scanner sc = new Scanner(System.in);
        String sql = "UPDATE socios SET ";
        boolean ponerAND=false;
        String And="AND ";
        int decision=sc.nextInt();
        System.out.println("******************************************************");
        System.out.println("*****             Biblioteca Muskiz               ****");
        System.out.println("******************************************************");
        System.out.println("    1-ID                        4-Dirección");
        System.out.println("    2-Nombre                    5-Telefono");
        System.out.println("    3-Apellidos                 6-Email");
        System.out.println("    7-Volver al inicio");

        switch (decision) {
            case 1:
                int id;
                System.out.println("\nIntroduce el id del socio a modificar:");
                id = sc.nextInt();

                if (ponerAND) {
                    sql+=And;
                }
                break;

            case 2:
                String nombre;
                System.out.println("Introduce el nuevo nombre del socio:");
                nombre = sc.nextLine();

                if (ponerAND) {
                    sql+=And;
                }
                break;

            case 3:
                String apellidos;
                System.out.println("Introduce los nuevos apellidos del socio:");
                apellidos = sc.nextLine();

                if (ponerAND) {
                    sql+=And;
                }
                break;

            case 4:
                String direccion;
                System.out.println("Introduce la nueva direccion del socio:");
                direccion = sc.nextLine();

                if (ponerAND) {
                    sql+=And;
                }
                break;

            case 5:
                String telefono;
                System.out.println("Introduce el nuevo telefono del socio:");
                telefono = sc.nextLine();

                if (ponerAND) {
                    sql+=And;
                }
                break;

            case 6:
                String email;
                System.out.println("Introduce el nuevo email del socio:");
                email = sc.nextLine();

                if (ponerAND) {
                    sql+=And;
                }
                break;

            case 7:
                String contraseña;
                System.out.println("Introduce la nueva contraseña del socio:");
                contraseña = sc.nextLine();

                if (ponerAND) {
                    sql+=And;
                }
                break;
        
            default:
                System.out.println("Escoge una opción de las dadas");
                break;
        }
    }

    public static void modificarLibros(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;

        }

        System.out.println("introduce el id del libro a modificar:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        String titulo;
        System.out.println("Introduce el nuevo titulo del libro:");
        titulo = sc.nextLine();

        int ISBN;
        System.out.println("Introduce el nuevo ISBN del libro:");
        ISBN = sc.nextInt();

        int numPags;
        System.out.println("Introduce el nuevo numero de paginas del libro:");
        numPags = sc.nextInt();

        int anioPublicacion;
        System.out.println("Introduce el nuevo año de publicacion del libro:");
        anioPublicacion = sc.nextInt();

        int ejemplares;
        System.out.println("Introduce el nuevo numero de ejemplares del libro:");
        ejemplares = sc.nextInt();
        String sql = "UPDATE libros (titulo, ISBN, numPags, anioPublicacion, ejemplares) SET (?, ?, ?, ?, ?) where id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setInt(2, ISBN);
            pstmt.setInt(3, numPags);
            pstmt.setInt(4, anioPublicacion);
            pstmt.setInt(5, ejemplares);
            pstmt.setInt(6, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Libro modificado correctamente.");
            } else {
                System.out.println("No se encontró el libro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el libro: " + e.getMessage());
        }
        sc.close();
    }

    public static void modificarPrestamos(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }

        int id;
        System.out.println("Introduce el id del prestamo a modificar:");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();


        Date fechaPrestamo = null;
        System.out.println("Introduce la nueva fecha de prestamo (YYYY-MM-DD):");
        String fechaPrestamoStr = sc.nextLine();
        try {
            fechaPrestamo = Date.valueOf(fechaPrestamoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha inválido. Usando valor por defecto.");
        }

        Date fechaDevolucion = null;
        fechaDevolucion = Function.fechaDevolucion(conn, fechaDevolucion);
        if (fechaDevolucion != null) 
        System.out.println("Introduce la nueva fecha de devolucion (YYYY-MM-DD):");
        String fechaDevolucionStr = sc.nextLine();

        String entregado;
        System.out.println("Introduce el nuevo estado de entrega (S/N):");
        entregado = sc.nextLine();

        System.out.println("Introduce el id del socio al que se hizo el prestamo a modificar:");
        id = sc.nextInt();

        int idLibro;
        System.out.println("Introduce el id del libro al que se hizo el prestamo a modificar:");
        idLibro = sc.nextInt();

        String sql = "UPDATE prestamos (fecha_prestamo, fecha_devolucion, entregado,id, id_libro) SET (?, ?, ?, ?, ?) where id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, fechaPrestamo);
            pstmt.setDate(2, fechaDevolucion);
            pstmt.setString(3, entregado);
            pstmt.setInt(4, id);
            pstmt.setInt(5, idLibro);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Prestamo modificado correctamente.");
            } else {
                System.out.println("No se encontró el prestamo con los datos proporcionados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el prestamo: " + e.getMessage());
        }
        sc.close();
    }

    public static void modificarAutores(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
        int id;
        System.out.println("introduce el id del autor a modificar:");
        Scanner sc = new Scanner(System.in); 
        id= sc.nextInt();
        
        String nombre;
        System.out.println("Introduce el nuevo nombre del autor:");
        nombre = sc.nextLine();

        String apellido1;
        System.out.println("Introduce el nuevo apellido1 del autor:");
        apellido1 = sc.nextLine();

        String apellido2;
        System.out.println("Introduce el nuevo apellido2 del autor:");
        apellido2 = sc.nextLine();

        String sql = "UPDATE INTO autores (id, nombre, apellido1, apellido2) SET (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido1);
            pstmt.setString(4, apellido2);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Autor modificado correctamente.");
            } else {
                System.out.println("No se encontró el autor con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el autor: " + e.getMessage());
        }
        sc.close();
    }
}

