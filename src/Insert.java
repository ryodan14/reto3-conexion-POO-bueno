import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;

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
        nombre=Function.mayusculas(nombre);

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        apellido=Function.mayusculas(apellido);

        String dni = "";
        while (true) {
            System.out.print("DNI: ");
            dni = sc.nextLine();
            dni=Function.mayusculas(dni);
        
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
        direccion=Function.mayusculas(direccion);

        String tlfn = "";
        while (true) {
            System.out.print("Teléfono: ");
            tlfn = sc.nextLine(); 
            tlfn=Function.mayusculas(tlfn);
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
            correo=Function.mayusculas(correo);
            if (Function.validarCorreo(correo)) {
                break;
            } else {
                System.out.println("Correo no válido. Introduzca un correo electrónico válido.");
            }
            
        }


        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        usuario=Function.mayusculas(usuario);

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();
        contraseña=Function.mayusculas(contraseña);
        
        
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
        String penalizado = "N"; 

        String fecha_alta = null;
        if (fecha_alta == null || fecha_alta.trim().isEmpty()) {
        fecha_alta = LocalDate.now().toString(); // yyyy-MM-dd
        }
            



        String sql = "INSERT INTO socios (id_socio, dni, nombre, apellido, direccion, tlfn, correo, usuario, contraseña, seguridad_social,penalizado,fecha_alta) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";

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
            pstmt.setString(11, penalizado); // Penalizado por defecto
            pstmt.setString(12, fecha_alta);

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
        titulo=Function.mayusculas(titulo);
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
        isbn=Function.mayusculas(isbn);
        if (Function.comprobarISBNLibro(conn, isbn)) {
            System.out.println("El ISBN ya está insertado. Por favor introduzca otro.");
        } else {
            break;
        }
    }
    int numPags;
    System.out.println("número de páginas: ");
    numPags = Integer.parseInt(sc.nextLine());

    int anioPubli;
    System.out.println("año de publicación: ");
    anioPubli = Integer.parseInt(sc.nextLine());

    int ejemplares;
    System.out.println("Cuantos ejemplares hay: ");
    ejemplares = Integer.parseInt(sc.nextLine());
    

    String sql = "INSERT INTO libros (id_libro, titulo, isbn,numPags,anioPubli,ejemplares) VALUES (?,?,?, ?, ?,?)";

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id_libro);
        pstmt.setString(2, titulo);
        pstmt.setString(3, isbn);
        pstmt.setInt(4, numPags);
        pstmt.setInt(5, anioPubli);
        pstmt.setInt(6, ejemplares);

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

    String fecha_inicio = null;
    if (fecha_inicio == null || fecha_inicio.trim().isEmpty()) {
        fecha_inicio = LocalDate.now().toString(); // yyyy-MM-dd
    }

    // Verificación del ID del socio
    System.out.print("ID del socio: ");
    String socio = sc.nextLine();

    // Comprobar si el socio está penalizado
    String penalizadoSql = "SELECT penalizado FROM socios WHERE id_socio = ?";
    try (PreparedStatement penalizadoStmt = conn.prepareStatement(penalizadoSql)) {
        penalizadoStmt.setString(1, socio);
        ResultSet rs = penalizadoStmt.executeQuery();
        if (rs.next()) {
            String estadoPenalizado = rs.getString("penalizado");
            if (estadoPenalizado != null && estadoPenalizado.equalsIgnoreCase("S")) {
                System.out.println("El socio con ID " + socio + " está penalizado y no puede realizar préstamos.");
                return;
            }
        } else {
            System.out.println("No se encontró el socio con ID " + socio + ".");
            return;
        }
    } catch (SQLException e) {
        System.out.println("Error al comprobar si el socio está penalizado: " + e.getMessage());
        return;
    }

    // Comprobar si tiene 3 préstamos sin devolver
    if (Function.comprobarIdSocioPrestamo(conn, socio)) {
        System.out.println("El socio con código " + socio + " ya tiene 3 préstamos sin devolver. No se le admiten más.");
        return;
    }

    String titulo;
    System.out.print("Título del libro: ");
    titulo = sc.nextLine();
    titulo=Function.mayusculas(titulo);

    
    int id_libro = obtenerIdLibroPorTitulo(conn, titulo);

    if (id_libro == 0) {
        System.out.println("No se encontró ningún libro con ese título.");
        return;
    }

    // Obtener los ejemplares disponibles del libro
    int ejemplares = obtenerEjemplares(conn, id_libro);

    if (ejemplares <= 0) {
        System.out.println("No hay ejemplares disponibles del libro " + titulo + " para prestar.");
        return;
    }

    // Si hay ejemplares disponibles, restar 1
    actualizarEjemplares(conn, id_libro, ejemplares - 1);

    // Si pasa todas las comprobaciones, procedemos con la inserción
    String sql = "INSERT INTO prestamos (codigo, fecha_inicio, socio, id_libro) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, codigo);
        pstmt.setString(2, fecha_inicio);
        pstmt.setString(3, socio);
        pstmt.setInt(4, id_libro);

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



    //INSERT DE AUTOR
    public static void InsertAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        String idAutor;
        while (true) {
            System.out.print("ID del autor: ");
            idAutor = sc.nextLine();
            if (Function.comprobarIdAutor(conn,idAutor)) {
                System.out.println("El Id de autor " + idAutor + " ya existe. Por favor, introduzca otro.");
            } else {
                break;
            }
        }


        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        nombre=Function.mayusculas(nombre);
        
        System.out.print("Primer apellido: ");
        String apellido1 = sc.nextLine();
        apellido1=Function.mayusculas(apellido1);

        System.out.print("Segundo apellido: ");
        String apellido2 = sc.nextLine();
        apellido2=Function.mayusculas(apellido2);

        if(Function.comprobarNombreAutor(conn,nombre,apellido1,apellido2)) { 
            System.out.println("El autor con nombre " + nombre + " " +apellido1 +" "+ apellido2 + " ya existe. Por favor, introduzca otro.");
            return; // Salir de la función sin continuar con la inserción
        }

        String sql = "INSERT INTO autores (id, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idAutor);
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
        descripcion=Function.mayusculas(descripcion);

        System.out.print("Tipo de penalización: ");
        String tipo = sc.nextLine();
        tipo=Function.mayusculas(tipo);

        System.out.println("Id del socio: ");
        String ID_Socio = sc.nextLine();

        // OJO: columna 'decripcion' estaba mal escrita
        String sql = "INSERT INTO penalizaciones (codigo, descripcion, tipo, ID_Socio) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, tipo);
            pstmt.setString(4, ID_Socio);

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
        // Después de insertar la penalización, actualizamos la columna 'penalizado' en la tabla 'socios'
        String updateSql = "UPDATE socios SET penalizado = 'S' WHERE id_socio = ?";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setString(1, ID_Socio);
            int updateFilas = updateStmt.executeUpdate();

            if (updateFilas > 0) {
                System.out.println("Socio marcado como penalizado ('S').");
            } else {
                System.out.println("No se pudo marcar el socio como penalizado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado de penalización del socio: " + e.getMessage());
        }

    }

    public static void PrestamoEntregado (Connection conn){
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
    
        System.out.print("Código del préstamo: ");
        String codigo = sc.nextLine();
    
        String fecha_devolucion = LocalDate.now().toString(); // yyyy-MM-dd

        int id_libro = obtenerIdLibroPorPrestamo (conn, codigo);

        if (id_libro == 0) {
            System.out.println("No se encontró ningún libro con ese título.");
            return;
        }
    
        // Obtener los ejemplares disponibles del libro
        int ejemplares = obtenerEjemplares(conn, id_libro);
    
        if (ejemplares <= 0) {
            System.out.println("No hay ejemplares disponibles del libro " + codigo + " para prestar.");
            return;
        }
    
        // Si hay ejemplares disponibles, restar 1
        actualizarEjemplares(conn, id_libro, ejemplares + 1);

    
        String sql = "UPDATE prestamos SET entregado = 'S', fecha_devolucion = ? WHERE codigo = ?";
    
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fecha_devolucion);  
            pstmt.setString(2, codigo);           
    
            int filas = pstmt.executeUpdate();
    
            if (filas > 0) {
                System.out.println("Préstamo marcado como entregado.");
            } else {
                System.out.println("No se pudo marcar el préstamo como entregado.");
            }
    
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al marcar el préstamo como entregado: " + e.getMessage());
        }
    }


    public static int obtenerIdLibroPorTitulo (Connection conn, String titulo) {
        String sql = "SELECT id_libro FROM libros WHERE UPPER(titulo) = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo.toUpperCase()); // Asegura coincidencia sin importar mayúsculas
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_libro");
            } else {
                return 0; // No se encontró ningún libro con ese título
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del libro: " + e.getMessage());
            return 0; // Error en la consulta
        }
    }
    public static int obtenerEjemplares(Connection conn, int id_libro) {
        String sql = "SELECT ejemplares FROM libros WHERE id_libro = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_libro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ejemplares");
            } else {
                return -1; // No existe el libro
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los ejemplares del libro: " + e.getMessage());
            return -2; // Error en la consulta
        }
    }
    public static void actualizarEjemplares(Connection conn, int id_libro, int ejemplares) {
        String sql = "UPDATE libros SET ejemplares = ? WHERE id_libro = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ejemplares);
            pstmt.setInt(2, id_libro);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar los ejemplares del libro: " + e.getMessage());
        }
    }

    public static int obtenerIdLibroPorPrestamo (Connection conn, String codigo) {
        String sql = "SELECT id_libro FROM libros WHERE id_libro in(select id_libro from prestamos where codigo = ?) ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_libro");
            } else {
                return 0; // No se encontró ningún libro con ese título
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del libro: " + e.getMessage());
            return 0; // Error en la consulta
        }
    }
}