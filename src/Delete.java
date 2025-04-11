import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    public static final Scanner sc=new Scanner(System.in);

    // DELETE DE SOCIOS
    public static void DeleteSocios(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        System.out.println("¿Cómo quieres borrar al usuario?");
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
                    boolean dniCorrecto = false;
                    String dni = "";
                    while (!dniCorrecto) {
                        dni = sc.nextLine();
                        dniCorrecto = Function.validarDNI(dni);
                    }
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

    // DELETE DE LIBROS
    public static void DeleteLibros(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        System.out.println("¿Cómo quieres borrar el libro?");
        System.out.println("1. Por ID");
        System.out.println("2. Por título");
        System.out.println("3. Por ISBN");
        System.out.print("Elige opción: ");
        String opcion = sc.nextLine();

        String sql = "";
        try {
            PreparedStatement pstmt = null;

            switch (opcion) {
                case "1":
                    System.out.print("Introduce el ID del libro: ");
                    String id = sc.nextLine();
                    sql = "DELETE FROM libros WHERE id_libro = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    break;

                case "2":
                    System.out.print("Introduce el título del libro: ");
                    String titulo = sc.nextLine();
                    sql = "DELETE FROM libros WHERE titulo = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, titulo);
                    break;

                case "3":
                    System.out.print("Introduce el ISBN: ");
                    String isbn = sc.nextLine();
                    sql = "DELETE FROM libros WHERE isbn = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, isbn);
                    break;

                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Libro borrado correctamente.");
            } else {
                System.out.println("No se encontró ningún libro con ese dato.");
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error al borrar el libro: " + e.getMessage());
        }
        
    }

    // DELETE DE PRÉSTAMOS
    public static void DeletePrestamos(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        String sql = "";
        try {
            PreparedStatement pstmt = null;

            System.out.print("Introduce el codigo del prestamos: ");
            String id = sc.nextLine();
            System.out.print("Introduce la fecha de inicio: ");
            String fecha_inicio = sc.nextLine();

            sql = "DELETE FROM prestamos WHERE codigo = ? AND fecha_inicio = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, fecha_inicio);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Préstamo borrado correctamente.");
            } else {
                System.out.println("No se encontró ningún préstamo con ese dato.");
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error al borrar el préstamo: " + e.getMessage());
        }
        
    }

    // DELETE DE AUTORES
    public static void DeleteAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        System.out.println("¿Cómo quieres borrar el autor?");
        System.out.println("1. Por ID");
        System.out.println("2. Por nombre");
        System.out.print("Elige opción: ");
        String opcion = sc.nextLine();

        String sql = "";
        try {
            PreparedStatement pstmt = null;

            switch (opcion) {
                case "1":
                    System.out.print("Introduce el ID del autor: ");
                    String id = sc.nextLine();
                    sql = "DELETE FROM autores WHERE id_autor = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    break;

                case "2":
                    System.out.print("Introduce el nombre del autor: ");
                    String nombre = sc.nextLine();
                    System.out.print("Introduce el primer apellido del autor: ");
                    String apellido1 = sc.nextLine();
                    System.out.print("Introduce el segundo apellido del autor: ");
                    String apellido2 = sc.nextLine();
                    sql = "DELETE FROM autores WHERE nombre = ? AND apellido1 = ? AND apellido2 = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombre);
                    pstmt.setString(2, apellido1);
                    pstmt.setString(3, apellido2);
                    break;

                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Autor borrado correctamente.");
            } else {
                System.out.println("No se encontró ningún autor con ese dato.");
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error al borrar el autor: " + e.getMessage());
        }
        
    }

    // DELETE DE PENALIZACIONES
    public static void DeletePenalizaciones(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
        
        System.out.print("¿Cuál es el código de la penalización? ");
        String id = sc.nextLine();

        String sql = "";
        try {
            PreparedStatement pstmt = null;

            sql = "DELETE FROM penalizacion WHERE codigo = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Penalización borrada correctamente.");
            } else {
                System.out.println("No se encontró ninguna penalización con ese dato.");
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error al borrar la penalización: " + e.getMessage());
        }
        
    }
}
