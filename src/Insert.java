import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;

public class Insert {
    public static final Scanner sc = new Scanner(System.in);

    // INSERTAR SOCIOS
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
        String nombre = Function.mayusculas(sc.nextLine());

        System.out.print("Apellido: ");
        String apellido = Function.mayusculas(sc.nextLine());

        String dni;
        while (true) {
            System.out.print("DNI: ");
            dni = Function.mayusculas(sc.nextLine());

            if (!Function.validarDNI(dni)) {
                System.out.println("DNI no válido.");
                continue;
            }

            if (Function.dniYaExiste(conn, dni)) {
                System.out.println("El DNI ya existe. Introduzca otro DNI.");
            } else {
                break;
            }
        }

        System.out.print("Dirección: ");
        String direccion = Function.mayusculas(sc.nextLine());

        String tlfn;
        while (true) {
            System.out.print("Teléfono: ");
            tlfn = Function.mayusculas(sc.nextLine());
            if (Function.validarTelf(tlfn)) break;
            System.out.println("Teléfono no válido.");
        }

        String correo;
        while (true) {
            System.out.print("Correo: ");
            correo = Function.mayusculas(sc.nextLine());
            if (Function.validarCorreo(correo)) break;
            System.out.println("Correo no válido.");
        }

        System.out.print("Usuario: ");
        String usuario = Function.mayusculas(sc.nextLine());

        System.out.print("Contraseña: ");
        String contraseña = Function.mayusculas(sc.nextLine());

        Long seguridad_social;
        while (true) {
            System.out.print("Seguridad Social (opcional): ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                seguridad_social = null;
                break;
            }
            try {
                seguridad_social = Long.parseLong(input);
                if (Function.validarSs(seguridad_social)) break;
                else System.out.println("Número inválido. Debe tener 12 dígitos.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Solo números.");
            }
        }

        String fecha_alta = LocalDate.now().toString();

        String sql = "INSERT INTO socios (id_socio, dni, nombre, apellido, direccion, tlfn, correo, usuario, contraseña, seguridad_social, fecha_alta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id_socio);
            pstmt.setString(2, dni);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido);
            pstmt.setString(5, direccion);
            pstmt.setString(6, tlfn);
            pstmt.setString(7, correo);
            pstmt.setString(8, usuario);
            pstmt.setString(9, contraseña);
            if (seguridad_social != null) {
                pstmt.setLong(10, seguridad_social);
            } else {
                pstmt.setNull(10, Types.BIGINT);
            }
            pstmt.setString(11, fecha_alta);

            int filas = pstmt.executeUpdate();
            System.out.println(filas > 0 ? "Socio insertado correctamente." : "No se pudo insertar el socio.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el socio: " + e.getMessage());
        }
    }

    // INSERT DE LIBROS
    public static void InsertLibros(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        String id_libro;
        while (true) {
            System.out.print("ID: ");
            id_libro = sc.nextLine();
            if (!Function.comprobarIdLibro(conn, id_libro)) break;
            System.out.println("ID ya existe.");
        }

        String titulo;
        while (true) {
            System.out.print("Título: ");
            titulo = Function.mayusculas(sc.nextLine());
            if (!Function.comprobarTituloLibro(conn, titulo)) break;
            System.out.println("Libro ya existente.");
        }

        String isbn;
        while (true) {
            System.out.print("ISBN: ");
            isbn = Function.mayusculas(sc.nextLine());
            if (!Function.comprobarISBNLibro(conn, isbn)) break;
            System.out.println("ISBN ya existente.");
        }

        System.out.print("Número de páginas: ");
        int numPags = Integer.parseInt(sc.nextLine());

        System.out.print("Año de publicación: ");
        int anioPubli = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO libros (id_libro, titulo, isbn, numPags, anioPubli) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id_libro);
            pstmt.setString(2, titulo);
            pstmt.setString(3, isbn);
            pstmt.setInt(4, numPags);
            pstmt.setInt(5, anioPubli);

            int filas = pstmt.executeUpdate();
            System.out.println(filas > 0 ? "Libro insertado correctamente." : "No se pudo insertar el libro.");
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
            if (!Function.comprobarIdPrestamo(conn, codigo)) break;
            System.out.println("Código ya existente.");
        }

        String fecha_inicio = LocalDate.now().toString();

        System.out.print("ID del socio: ");
        String socio = sc.nextLine();

        String sqlPenalizado = "SELECT penalizado FROM socios WHERE id_socio = ?";
        try (PreparedStatement penalizadoStmt = conn.prepareStatement(sqlPenalizado)) {
            penalizadoStmt.setString(1, socio);
            ResultSet rs = penalizadoStmt.executeQuery();
            if (rs.next()) {
                if ("S".equalsIgnoreCase(rs.getString("penalizado"))) {
                    System.out.println("El socio está penalizado.");
                    return;
                }
            } else {
                System.out.println("Socio no encontrado.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar penalización: " + e.getMessage());
            return;
        }

        if (Function.comprobarIdSocioPrestamo(conn, socio)) {
            System.out.println("El socio tiene 3 préstamos sin devolver.");
            return;
        }

        String sql = "INSERT INTO prestamos (codigo, fecha_inicio, socio) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setString(2, fecha_inicio);
            pstmt.setString(3, socio);
            int filas = pstmt.executeUpdate();
            System.out.println(filas > 0 ? "Préstamo insertado correctamente." : "No se pudo insertar el préstamo.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el préstamo: " + e.getMessage());
        }
    }

    // INSERTAR AUTOR
    public static void InsertAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        String idAutor;
        while (true) {
            System.out.print("ID del autor: ");
            idAutor = sc.nextLine();
            if (!Function.comprobarIdAutor(conn, idAutor)) break;
            System.out.println("ID ya existe.");
        }

        System.out.print("Nombre: ");
        String nombre = Function.mayusculas(sc.nextLine());

        System.out.print("Primer apellido: ");
        String apellido1 = Function.mayusculas(sc.nextLine());

        System.out.print("Segundo apellido: ");
        String apellido2 = Function.mayusculas(sc.nextLine());

        if (Function.comprobarNombreAutor(conn, nombre, apellido1, apellido2)) {
            System.out.println("Autor ya existe.");
            return;
        }

        String sql = "INSERT INTO autores (id, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idAutor);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido1);
            pstmt.setString(4, apellido2);
            int filas = pstmt.executeUpdate();
            System.out.println(filas > 0 ? "Autor insertado correctamente." : "No se pudo insertar el autor.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el autor: " + e.getMessage());
        }
    }

    // INSERTAR PENALIZACIÓN
    public static void InsertPenalizaciones(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        System.out.print("Código de penalización: ");
        String codigo = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = Function.mayusculas(sc.nextLine());

        System.out.print("Tipo: ");
        String tipo = Function.mayusculas(sc.nextLine());

        System.out.print("ID del socio: ");
        String ID_Socio = sc.nextLine();

        String sql = "INSERT INTO penalizaciones (codigo, descripcion, tipo, ID_Socio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, tipo);
            pstmt.setString(4, ID_Socio);
            pstmt.executeUpdate();
            System.out.println("Penalización insertada.");
        } catch (SQLException e) {
            System.out.println("Error al insertar penalización: " + e.getMessage());
        }

        // Actualizar estado penalizado
        String updateSql = "UPDATE socios SET penalizado = 'S' WHERE id_socio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setString(1, ID_Socio);
            pstmt.executeUpdate();
            System.out.println("Socio marcado como penalizado.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar penalización: " + e.getMessage());
        }
    }

    // MARCAR PRÉSTAMO COMO ENTREGADO
    public static void PrestamoEntregado(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        System.out.print("Código del préstamo: ");
        String codigo = sc.nextLine();
        String fecha_devolucion = LocalDate.now().toString();

        String sql = "UPDATE prestamos SET entregado = 'S', fecha_devolucion = ? WHERE codigo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fecha_devolucion);
            pstmt.setString(2, codigo);
            int filas = pstmt.executeUpdate();
            System.out.println(filas > 0 ? "Préstamo entregado." : "No se pudo entregar el préstamo.");
        } catch (SQLException e) {
            System.out.println("Error al entregar préstamo: " + e.getMessage());
        }
    }
}

