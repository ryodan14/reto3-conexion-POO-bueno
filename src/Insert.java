import java.util.Scanner;
import java.sql.*;

public class Insert {

    public static void InsertSocios(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        String id_socio = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        boolean dniCorrecto=false;
        System.out.print("DNI: ");
        String dni="";
        while (dniCorrecto==false) {
            dni = sc.nextLine();
            if (Function.validarDNI(dni)) {
                dniCorrecto=true;
                System.out.println("DNI válido");
            }else{
                dniCorrecto=false;
                System.out.println("DNI no válido");
            }
        }

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String tlfn = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        System.out.print("Seguridad Social: ");
        String seguridad_social = sc.nextLine();

        String sql = "INSERT INTO socios (id_socio, dni, nombre, apellido, direccion, tlfn, correo, usuario, contraseña, seguridad_social) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_socio);
            pstmt.setString(2, dni);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido);
            pstmt.setString(5, direccion);
            pstmt.setString(6, tlfn);
            pstmt.setString(7, correo);
            pstmt.setString(8, usuario);
            pstmt.setString(9, contraseña);
            pstmt.setString(10, seguridad_social);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el usuario.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
        sc.close(); 
    }
    public static void InsertLibros(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        String id_libro = sc.nextLine();

        System.out.print("Titulo: ");
        String titulo = sc.nextLine();

        System.out.print("Isbn: ");
        String isbn= sc.nextLine();

        String sql = "INSERT INTO libros (id_libro,titulo,isbn) " +
                    "VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_libro);
            pstmt.setString(2, titulo);
            pstmt.setString(3, isbn);


            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Libro insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el libro.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el libro: " + e.getMessage());
        }
        sc.close(); 
    }
    public static void InsertPrestamos(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();

        System.out.print("Fecha de inicio: ");
        String fecha_inicio = sc.nextLine();

        System.out.print("Fecha de devolución: ");
        String fecha_devolucion= sc.nextLine();

        System.out.print("entregado: ");
        String entregado= sc.nextLine();

        System.out.print("Socio: ");
        String socio = sc.nextLine();

        String sql = "INSERT INTO prestamos (codigo,fecha_inicio,fecha_devolucion,entregado,socio) " +
                    "VALUES (?, ?, ?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.setString(2, fecha_inicio);
            pstmt.setString(3, fecha_devolucion);
            pstmt.setString(4, entregado);
            pstmt.setString(5, socio);



            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Prestamo insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el prestamo.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el prestamo: " + e.getMessage());
        }
        sc.close(); 
    }
    public static void InsertAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("id del autor: ");
        String id = sc.nextLine();

        System.out.print("nombre: ");
        String nombre = sc.nextLine();

        System.out.print("primer apellido: ");
        String apellido1= sc.nextLine();

        System.out.print("Segundo apellido: ");
        String apellido2= sc.nextLine();



        String sql = "INSERT INTO autores (id,nombre,apellido1,apellido2) " +
                    "VALUES (?, ?, ?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido1);  
            pstmt.setString(4, apellido2);



            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Autor insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el Autor.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el autor: " + e.getMessage());
        }
        sc.close(); 
    }
    public static void InsertPenalizaciones(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("codigo de penalización: ");
        String codigo = sc.nextLine();

        System.out.print("Descripción de la penalización: ");
        String descripcion = sc.nextLine();

        System.out.print("tipo de penalizacion: ");
        String apellido1= sc.nextLine();

        System.out.print("Segundo apellido: ");
        String apellido2= sc.nextLine();



        String sql = "INSERT INTO autores (id,nombre,apellido1,apellido2) " +
                    "VALUES (?, ?, ?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido1);  
            pstmt.setString(4, apellido2);



            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Autor insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el Autor.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el autor: " + e.getMessage());
        }
        sc.close(); 
    }
}
