import java.util.Scanner;
import java.sql.*;

public class Insert {

    //INSERTAR SOCIOS
    public static void InsertSocios(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
    
        Scanner sc = new Scanner(System.in);
        String id_socio;
    
        while (true) {
            System.out.print("ID: ");
            id_socio = sc.nextLine();
    
            if (Function.comprobarIdSocio(conn, id_socio)) {
                System.out.println("ID ya existe. Introduzca otro ID.");
            } else {
                break;
            }
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        String dni = "";
        while (true) {
            System.out.print("DNI: ");
            dni = sc.nextLine();
            if (Function.validarDNI(dni)) {
                System.out.println("DNI válido.");
                break;
            } else {
                System.out.println("DNI no válido.");
            }
        }

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        while(true) {
            System.out.print("Teléfono: ");
            String tlfn = sc.nextLine();
            if (Function.validarTelf(tlfn)) {
                break;
            } else {
                System.out.println("Teléfono no válido. Introduzca un teléfono válido.");
            }
        }
        
        String correo;

        while (true) {
            System.out.print("Correo: ");
            correo = sc.nextLine();
            if (Function.validarCorreo(correo)) {
                break;
            } else {
                System.out.println("Correo no válido. Introduzca un correo electrónico válido.");
            }
            
        }


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
                System.out.println("Socio insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el socio.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el socio: " + e.getMessage());
        }
        
    }

    public static void InsertLibros(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        String id_libro = sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        String sql = "INSERT INTO libros (id_libro, titulo, isbn) VALUES (?, ?, ?)";

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
        
    }

    public static void InsertPrestamos(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
    
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Código: ");
        String codigo = sc.nextLine();
    
        System.out.print("Fecha de inicio: ");
        String fecha_inicio = sc.nextLine();
    
        String entregado = "";
        while (true) {
            System.out.print("¿Entregado? (S/N): ");
            entregado = sc.nextLine();
    
            if (entregado.equalsIgnoreCase("S") || entregado.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca 'S' para sí o 'N' para no.");
            }
        }
    
        String fecha_devolucion = null;
    
        if (entregado.equalsIgnoreCase("S")) {
            entregado = "S";
            System.out.print("Fecha de devolución: ");
            fecha_devolucion = sc.nextLine();
        } else {
            entregado = "N";
            fecha_devolucion = null; 
        }
    
        System.out.print("ID del socio: ");
        String socio = sc.nextLine();
    
        String sql = "INSERT INTO prestamos (codigo, fecha_inicio, fecha_devolucion, entregado, socio) VALUES (?, ?, ?, ?, ?)";
    
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.setString(2, fecha_inicio);
            pstmt.setString(3, fecha_devolucion); // aunque sea null, se puede pasar
            pstmt.setString(4, entregado);
            pstmt.setString(5, socio);
    
            int filas = pstmt.executeUpdate();
    
            if (filas > 0) {
                ;
            } else {
                System.out.println("No se pudo insertar el préstamo.");
            }
    
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el préstamo: " + e.getMessage());
        }
    
        
    }
    

    public static void InsertAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("ID del autor: ");
        String id = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Primer apellido: ");
        String apellido1 = sc.nextLine();

        System.out.print("Segundo apellido: ");
        String apellido2 = sc.nextLine();

        String sql = "INSERT INTO autores (id, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";

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
                System.out.println("No se pudo insertar el autor.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el autor: " + e.getMessage());
        }
        
    }

    public static void InsertPenalizaciones(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Código de penalización: ");
        String codigo = sc.nextLine();

        System.out.print("Descripción de la penalización: ");
        String descripcion = sc.nextLine();

        System.out.print("Tipo de penalización: ");
        String tipo = sc.nextLine();

        // OJO: columna 'decripcion' estaba mal escrita
        String sql = "INSERT INTO penalizaciones (codigo, descripcion, tipo) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, tipo);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Penalización insertada correctamente.");
            } else {
                System.out.println("No se pudo insertar la penalización.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar la penalización: " + e.getMessage());
        }
        
    }
}
