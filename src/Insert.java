import java.util.Scanner;
import java.sql.*;

public class Insert {
    public static final Scanner sc=new Scanner(System.in);

    //INSERTAR SOCIOS
    public static void InsertSocios(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
    
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
        
            if (!Function.validarDNI(dni)) {
                System.out.println("DNI no válido.");
                continue;
            }
            System.out.println("comprobando si el DNI no esta repetido...");
            // Comprobar si el DNI ya existe en la base de datos
            if (Function.dniYaExiste(conn, dni)) {
                System.out.println("El DNI ya existe. Introduzca otro DNI.");
                continue;
            }
        
            System.out.println("DNI válido y único.");
            break; // Sale del bucle
        }
        
        

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        String tlfn = "";
        while (true) {
            System.out.print("Teléfono: ");
            tlfn = sc.nextLine(); 
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
        
        
        Long seguridad_social ;

        while (true) {
            System.out.print("Seguridad Social: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                // Vacío, lo tomamos como válido y opcional
                seguridad_social = null;
                break;
            }
        
            try {
                seguridad_social = Long.parseLong(input);
        
                if (Function.validarSs(seguridad_social)) {
                    break;
                } else {
                    System.out.println("Número no válido. Debe tener 12 dígitos.");
                }
        
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Introduce solo números.");
            }
        }
        
            



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
            pstmt.setObject(10, seguridad_social, java.sql.Types.BIGINT);

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
//INSERT DE LIBROS
public static void InsertLibros(Connection conn) {
    if (conn == null) {
        System.out.println("No hay conexión con la base de datos.");
        return;
    }

    String id_libro;
    while (true) {
        System.out.print("ID: ");
        id_libro = sc.nextLine();
        if (Function.comprobarIdLibro(conn, id_libro)) {
            System.out.println("ID ya existe. Introduzca otro ID.");
        } else {
            break;
        }
    }

    String titulo;
    while (true) {
        System.out.print("Titulo: ");
        titulo = sc.nextLine();
        if (Function.comprobarTituloLibro(conn, titulo)) {
            System.out.println("El libro ya está insertado. Por favor introduzca otro.");
        } else {
            break;
        }
    }

    String isbn;
    while (true) {
        System.out.print("ISBN: ");
        isbn = sc.nextLine();
        if (Function.comprobarISBNLibro(conn, isbn)) {
            System.out.println("El ISBN ya está insertado. Por favor introduzca otro.");
        } else {
            break;
        }
    }

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


// INSERT DE PRÉSTAMOS
public static void InsertPrestamos(Connection conn) {
    if (conn == null) {
        System.out.println("No hay conexión con la base de datos.");
        return;
    }

    String codigo;
    while (true) {
        System.out.print("Código: ");
        codigo = sc.nextLine();
        if (Function.comprobarIdPrestamo(conn, codigo)) {
            System.out.println("El préstamo con código " + codigo + " ya existe. Por favor, introduzca otro.");
        } else {
            break;
        }
    }

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

    // Verificación del ID del socio
    System.out.print("ID del socio: ");
    String socio = sc.nextLine();

    if (Function.comprobarIdSocioPrestamo(conn, socio)) {
        // Si el socio tiene 3 préstamos sin devolver, mostrar el mensaje y salir sin insertar
        System.out.println("El socio con código " + socio + " ya tiene 3 préstamos sin devolver. No se le admiten más.");
        return; // Salir de la función sin continuar con la inserción
    }
    // Si pasa la comprobación, procedemos con la inserción
    String sql = "INSERT INTO prestamos (codigo, fecha_inicio, fecha_devolucion, entregado, socio) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, codigo);
        pstmt.setString(2, fecha_inicio);
        pstmt.setString(3, fecha_devolucion); // Puede ser null
        pstmt.setString(4, entregado);
        pstmt.setString(5, socio);

        int filas = pstmt.executeUpdate();

        if (filas > 0) {
            System.out.println("Préstamo insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el préstamo.");
        }

    } catch (SQLException e) {
        System.out.println("Error al insertar el préstamo: " + e.getMessage());
    }
}


    public static void InsertAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

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

            if (filas < 0) {
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
