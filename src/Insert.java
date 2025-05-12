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
    
        int id_socio;
        while (true) {
            System.out.print("ID: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                System.out.println("El ID no puede estar en blanco.");
                continue;
            }
        
            try {
                id_socio = Integer.parseInt(input);
        
                if (Function.comprobarIdSocio(conn, id_socio)) {
                    System.out.println("ID ya existe. Introduzca otro ID.");
                } else {
                    break; // ID válido y no duplicado
                }
        
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Introduzca solo números.");
            }
        }
        
        

        String nombre;
        while (true) {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();
            if (!nombre.isEmpty()) {
                nombre = Function.mayusculas(nombre);
                break;
            } else {
                System.out.println("El nombre no puede estar en blanco.");
            }
        }
        

        String apellidos;
        while (true) {
            System.out.print("Apellidos: ");
            apellidos = sc.nextLine().trim();
            if (!apellidos.isEmpty()) {
                apellidos = Function.mayusculas(apellidos);
                break;
            } else {
                System.out.println("Los apellidos no puede estar en blanco.");
            }
        }
        

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
        
        

        String direccion;
        while (true) {
            System.out.print("Dirección: ");
            direccion = sc.nextLine().trim();
            if (!direccion.isEmpty()) {
                direccion = Function.mayusculas(direccion);
                break;
            } else {
                System.out.println("La dirección no puede estar en blanco.");
            }
        }
        

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


        String usuario;
        while (true) {
            System.out.print("Usuario: ");
            usuario = sc.nextLine().trim();
            if (!usuario.isEmpty()) {
                usuario = Function.mayusculas(usuario);
                break;
            } else {
                System.out.println("El usuario no puede estar en blanco.");
            }
        }
        

        String contraseña;
        while (true) {
            System.out.print("Contraseña: ");
            contraseña = sc.nextLine().trim();
            if (!contraseña.isEmpty()) {
                break;
            } else {
                System.out.println("La contraseña no puede estar en blanco.");
            }
        }
        
        
        
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
            pstmt.setInt(1, id_socio);
            pstmt.setString(2, dni);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellidos);
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

        int id_libro;
        while (true) {
            System.out.print("ID del libro: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                System.out.println("El ID no puede estar en blanco.");
                continue;
            }
        
            try {
                id_libro = Integer.parseInt(input);
        
                if (Function.comprobarIdLibro(conn, id_libro)) {
                    System.out.println("ID ya existe. Introduzca otro ID.");
                } else {
                    break; // ID válido y no duplicado
                }
        
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Introduzca solo números.");
            }
        }

    String titulo;
    while (true) {
        System.out.print("Titulo: ");
        titulo = sc.nextLine().trim();
    
        if (titulo.isEmpty()) {
            System.out.println("El titulo no puede estar en blanco.");
            continue;
        }
    
        titulo = Function.mayusculas(titulo);
    
        if (Function.comprobarTituloLibro(conn, titulo)) {
            System.out.println("ISBN ya existe. Introduzca otro ISBN.");
        } else {
            break; // Campo no vacío y ID no duplicado
        }
    }


    String isbn;
    while (true) {
        System.out.print("ISBN: ");
        isbn = sc.nextLine().trim();
    
        if (isbn.isEmpty()) {
            System.out.println("El ISBN no puede estar en blanco.");
            continue;
        }
    
        isbn = Function.mayusculas(isbn);
    
        if (Function.comprobarISBNLibro(conn, isbn)) {
            System.out.println("ISBN ya existe. Introduzca otro ISBN.");
        } else {
            break; // Campo no vacío y ID no duplicado
        }
    }

    int numPags;
    while (true) {
        System.out.print("Número de páginas: ");
        String input = sc.nextLine().trim();
    
        if (input.isEmpty()) {
            System.out.println("El número de páginas no puede estar en blanco.");
            continue;
        }
    
        try {
            numPags = Integer.parseInt(input);
            break; // Valor válido, salimos del bucle
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un número válido.");
        }
    }
    

    int anioPubli;
    while (true) {
        System.out.print("Año de publicación: ");
        String input = sc.nextLine().trim();
    
        if (input.isEmpty()) {
            System.out.println("El año de publicación no puede estar en blanco.");
            continue;
        }
    
        try {
            anioPubli = Integer.parseInt(input);
            break; // Valor válido, salimos del bucle
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un año válido.");
        }
    }
    

    int ejemplares;
    while (true) {
        System.out.print("Número de ejemplares: ");
        String input = sc.nextLine().trim();
    
        if (input.isEmpty()) {
            System.out.println("El número de ejemplares no puede estar en blanco.");
            continue;
        }
    
        try {
            ejemplares = Integer.parseInt(input);
            break; // Valor válido, salimos del bucle
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un número válido.");
        }
    }
    

    String sql = "INSERT INTO libros (id_libro, titulo, isbn,numPags,anioPubli,ejemplares) VALUES (?,?,?, ?, ?,?)";

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_libro);
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

    int codigo;
    while (true) {
        System.out.print("ID del prestamo: ");
        String input = sc.nextLine().trim();
    
        if (input.isEmpty()) {
            System.out.println("El ID no puede estar en blanco.");
            continue;
        }
    
        try {
            codigo = Integer.parseInt(input);
    
            if (Function.comprobarIdPrestamo(conn, codigo)) {
                System.out.println("ID ya existe. Introduzca otro ID.");
            } else {
                break; // ID válido y no duplicado
            }
    
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Introduzca solo números.");
        }
    }

    String fecha_inicio = null;
    if (fecha_inicio == null || fecha_inicio.trim().isEmpty()) {
        fecha_inicio = LocalDate.now().toString(); // yyyy-MM-dd
    }

    int socio;
    while (true) {
        System.out.print("ID del socio: ");
        String input = sc.nextLine().trim();
    
        if (input.isEmpty()) {
            System.out.println("El ID del socio no puede estar en blanco.");
            continue;
        }
    
        try {
            socio = Integer.parseInt(input);
            break; // Valor válido, salimos del bucle
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un número válido.");
        }
    }
    // Comprobar si el socio está penalizado
    String penalizadoSql = "SELECT penalizado FROM socios WHERE id_socio = ?";
    try (PreparedStatement penalizadoStmt = conn.prepareStatement(penalizadoSql)) {
        penalizadoStmt.setInt(1, socio);
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
    while (true) {
        System.out.print("Título del libro: ");
        titulo = sc.nextLine().trim();
    
        if (titulo.isEmpty()) {
            System.out.println("El título no puede estar en blanco.");
            continue;
        }
    
        titulo = Function.mayusculas(titulo);
        break; // Exit the loop when the title is valid
    }
    
    int id_libro = Function.obtenerIdLibroPorTitulo(conn, titulo);

    if (id_libro == 0) {
        System.out.println("No se encontró ningún libro con ese título.");
        return;
    }

    // Obtener los ejemplares disponibles del libro
    int ejemplares = Function.obtenerEjemplares(conn, id_libro);

    if (ejemplares <= 0) {
        System.out.println("No hay ejemplares disponibles del libro " + titulo + " para prestar.");
        return;
    }

    // Si hay ejemplares disponibles, restar 1
    Function.actualizarEjemplares(conn, id_libro, ejemplares - 1);

    // Si pasa todas las comprobaciones, procedemos con la inserción
    String sql = "INSERT INTO prestamos (codigo, fecha_inicio, socio, id_libro) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, codigo);
        pstmt.setString(2, fecha_inicio);
        pstmt.setInt(3, socio);
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

        int id;
        while (true) {
            System.out.print("ID del autor: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                System.out.println("El ID no puede estar en blanco.");
                continue;
            }
        
            try {
                id = Integer.parseInt(input);
        
                if (Function.comprobarIdAutor(conn, id)) {
                    System.out.println("ID ya existe. Introduzca otro ID.");
                } else {
                    break; // ID válido y no duplicado
                }
        
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Introduzca solo números.");
            }
        }


        String nombre;
        while (true) {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();
            if (!nombre.isEmpty()) {
                nombre = Function.mayusculas(nombre);
                break;
            } else {
                System.out.println("El nombre no puede estar en blanco.");
            }
        }
        
        String apellido1;
        while (true) {
            System.out.print("Primer apellido: ");
            apellido1 = sc.nextLine().trim();
            if (!apellido1.isEmpty()) {
                apellido1 = Function.mayusculas(apellido1);
                break;
            } else {
                System.out.println("El nombre no puede estar en blanco.");
            }
        }

        String apellido2;
        while (true) {
            System.out.print("Segundo apellido: ");
            apellido2 = sc.nextLine().trim();
            if (!apellido2.isEmpty()) {
                apellido2 = Function.mayusculas(apellido2);
                break;
            } else {
                System.out.println("El nombre no puede estar en blanco.");
            }
        }

        if(Function.comprobarNombreAutor(conn,nombre,apellido1,apellido2)) { 
            System.out.println("El autor con nombre " + nombre + " " +apellido1 +" "+ apellido2 + " ya existe. Por favor, introduzca otro.");
            return; // Salir de la función sin continuar con la inserción
        }

        String sql = "INSERT INTO autores (id, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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
    

    //INSERT DE PENALIZACIONES
    public static void InsertPenalizaciones(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        int codigo;
        while (true) {
            System.out.print("ID penalización: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                System.out.println("El ID no puede estar en blanco.");
                continue;
            }
        
            try {
                codigo = Integer.parseInt(input);
        
                if (Function.comprobarIdPenalizado(conn, codigo)) {
                    System.out.println("ID ya existe. Introduzca otro ID.");
                } else {
                    break; // ID válido y no duplicado
                }
        
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Introduzca solo números.");
            }
        }

        String descripcion;
        while (true) {
            System.out.print("Describe la penalización: ");
            descripcion = sc.nextLine().trim();
            if (!descripcion.isEmpty()) {
                descripcion = Function.mayusculas(descripcion);
                break;
            } else {
                System.out.println("La descripción no puede estar en blanco.");
            }
        }

        String tipo;
        while (true) {
            System.out.print("Tipo de penalización: ");
            tipo = sc.nextLine().trim();
            if (!tipo.isEmpty()) {
                tipo = Function.mayusculas(tipo);
                break;
            } else {
                System.out.println("La descripción no puede estar en blanco.");
            }
        }

        int  ID_Socio;
        while (true) {
            System.out.print("ID del socio: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                System.out.println("El ID no puede estar en blanco.");
                continue;
            }
        
            try {
                ID_Socio = Integer.parseInt(input);
        
                if (Function.comprobarIdSocio(conn, ID_Socio)) {
                    break; // ID válido y no duplicado
                } else {
                    System.out.println("ID no existe. Introduzca otro ID.");
                }
        
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Introduzca solo números.");
            }
        }
        // OJO: columna 'decripcion' estaba mal escrita
        String sql = "INSERT INTO penalizaciones (codigo, descripcion, tipo, ID_Socio) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, tipo);
            pstmt.setInt(4, ID_Socio);

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
            updateStmt.setInt(1, ID_Socio);
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

    //insertar entrega del prestamo
    public static void PrestamoEntregado (Connection conn){
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
    
        int codigo;
        while (true) {
            System.out.print("código del prestamo: ");
            String input = sc.nextLine().trim();
        
            if (input.isEmpty()) {
                System.out.println("El ID no puede estar en blanco.");
                continue;
            }
        
            try {
                codigo = Integer.parseInt(input);
        
                if (Function.comprobarIdSocio(conn, codigo)) {
                    System.out.println("ID ya existe. Introduzca otro ID.");
                } else {
                    break; // ID válido y no duplicado
                }
        
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Introduzca solo números.");
            }
        }
    
        String fecha_devolucion = LocalDate.now().toString(); // yyyy-MM-dd

        int id_libro = Function.obtenerIdLibroPorPrestamo (conn, codigo);

        if (id_libro == 0) {
            System.out.println("No se encontró ningún libro con ese título.");
            return;
        }
    
        // Obtener los ejemplares disponibles del libro
        int ejemplares = Function.obtenerEjemplares(conn, id_libro);
    
        if (ejemplares <= 0) {
            System.out.println("No hay ejemplares disponibles del libro " + codigo + " para prestar.");
            return;
        }
    
        // Si hay ejemplares disponibles, restar 1
        Function.actualizarEjemplares(conn, id_libro, ejemplares + 1);

    
        String sql = "UPDATE prestamos SET entregado = 'S', fecha_devolucion = ? WHERE codigo = ?";
    
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fecha_devolucion);  
            pstmt.setInt(2, codigo);           
    
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
}

//todas las inserts hechas aleluya:)