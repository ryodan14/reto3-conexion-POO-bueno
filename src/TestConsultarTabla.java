import java.sql.*;
import java.util.Scanner;

public class TestConsultarTabla {
    Scanner teclado = new Scanner(System.in);
    public void consultarTablaSocios(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        
        int offset = 0;
        int limit = 5;
        String sql = "SELECT * FROM socios ORDER BY id_socio LIMIT ? OFFSET ?";

        boolean salir = false;
        while (!salir) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, limit);
                pstmt.setInt(2, offset);

                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("\nNo hay más datos. Volverás a la página anterior si pulsas [P]:");
                    String opcion = teclado.nextLine();
                    if (opcion.equalsIgnoreCase("P") && offset >= limit) {
                        offset -= limit;
                    }
                    rs.close();
                    pstmt.close();
                    continue;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                String[][] datos = new String[limit][columnas];
                int filasLeidas = 0;
                int[] anchos = new int[columnas];
                for (int i = 0; i < columnas; i++) {
                    anchos[i] = meta.getColumnName(i + 1).length();
                }

                while (rs.next()) {
                    for (int i = 0; i < columnas; i++) {
                        String valor = rs.getString(i + 1);
                        if (valor == null) valor = "NULL";
                        datos[filasLeidas][i] = valor;
                        if (valor.length() > anchos[i]) anchos[i] = valor.length();
                    }
                    filasLeidas++;
                }

                rs.close();
                pstmt.close();

                System.out.println("\n--- Página " + (offset / limit + 1) + " ---");

                for (int i = 0; i < columnas; i++) {
                    System.out.printf("%-" + (anchos[i] + 2) + "s", meta.getColumnName(i + 1));
                }
                System.out.println();
                for (int i = 0; i < columnas; i++) {
                    System.out.print("-".repeat(anchos[i] + 2));
                }
                System.out.println();

                for (int fila = 0; fila < filasLeidas; fila++) {
                    for (int col = 0; col < columnas; col++) {
                        System.out.printf("%-" + (anchos[col] + 2) + "s", datos[fila][col]);
                    }
                    System.out.println();
                }

                System.out.println("\n[N] Siguiente | [P] Anterior | [S] Salir");
                String opcion = teclado.nextLine();

                switch (opcion.toUpperCase()) {
                    case "N":
                        offset += limit;
                        break;
                    case "P":
                        if (offset >= limit) offset -= limit;
                        else System.out.println("Ya estás en la primera página.");
                        break;
                    case "S":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar: " + e.getMessage());
            }
        }

        System.out.println("\nSaliendo de la paginación...");
    }

    public void consultarTablaLibros(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
        int offset = 0;
        int limit = 5;
        String sql = "SELECT * FROM libros ORDER BY id_libro LIMIT ? OFFSET ?";

        boolean salir = false;
        while (!salir) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, limit);
                pstmt.setInt(2, offset);

                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("\nNo hay más datos. Volverás a la página anterior si pulsas [P]:");
                    String opcion = teclado.nextLine();
                    if (opcion.equalsIgnoreCase("P") && offset >= limit) {
                        offset -= limit;
                    }
                    rs.close();
                    pstmt.close();
                    continue;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                String[][] datos = new String[limit][columnas];
                int filasLeidas = 0;
                int[] anchos = new int[columnas];
                for (int i = 0; i < columnas; i++) {
                    anchos[i] = meta.getColumnName(i + 1).length();
                }

                while (rs.next()) {
                    for (int i = 0; i < columnas; i++) {
                        String valor = rs.getString(i + 1);
                        if (valor == null) valor = "NULL";
                        datos[filasLeidas][i] = valor;
                        if (valor.length() > anchos[i]) anchos[i] = valor.length();
                    }
                    filasLeidas++;
                }

                rs.close();
                pstmt.close();

                System.out.println("\n--- Página " + (offset / limit + 1) + " ---");

                for (int i = 0; i < columnas; i++) {
                    System.out.printf("%-" + (anchos[i] + 2) + "s", meta.getColumnName(i + 1));
                }
                System.out.println();
                for (int i = 0; i < columnas; i++) {
                    System.out.print("-".repeat(anchos[i] + 2));
                }
                System.out.println();

                for (int fila = 0; fila < filasLeidas; fila++) {
                    for (int col = 0; col < columnas; col++) {
                        System.out.printf("%-" + (anchos[col] + 2) + "s", datos[fila][col]);
                    }
                    System.out.println();
                }

                System.out.println("\n[N] Siguiente | [P] Anterior | [S] Salir");
                String opcion = teclado.nextLine();

                switch (opcion.toUpperCase()) {
                    case "N":
                        offset += limit;
                        break;
                    case "P":
                        if (offset >= limit) offset -= limit;
                        else System.out.println("Ya estás en la primera página.");
                        break;
                    case "S":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar: " + e.getMessage());
            }
        }

        System.out.println("\nSaliendo de la paginación...");
    }
    public void consultarTablaPrestamos(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
        int offset = 0;
        int limit = 5;
        String sql = "SELECT * FROM prestamos ORDER BY codigo LIMIT ? OFFSET ?";

        boolean salir = false;
        while (!salir) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, limit);
                pstmt.setInt(2, offset);

                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("\nNo hay más datos. Volverás a la página anterior si pulsas [P]:");
                    String opcion = teclado.nextLine();
                    if (opcion.equalsIgnoreCase("P") && offset >= limit) {
                        offset -= limit;
                    }
                    rs.close();
                    pstmt.close();
                    continue;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                String[][] datos = new String[limit][columnas];
                int filasLeidas = 0;
                int[] anchos = new int[columnas];
                for (int i = 0; i < columnas; i++) {
                    anchos[i] = meta.getColumnName(i + 1).length();
                }

                while (rs.next()) {
                    for (int i = 0; i < columnas; i++) {
                        String valor = rs.getString(i + 1);
                        if (valor == null) valor = "NULL";
                        datos[filasLeidas][i] = valor;
                        if (valor.length() > anchos[i]) anchos[i] = valor.length();
                    }
                    filasLeidas++;
                }

                rs.close();
                pstmt.close();

                System.out.println("\n--- Página " + (offset / limit + 1) + " ---");

                for (int i = 0; i < columnas; i++) {
                    System.out.printf("%-" + (anchos[i] + 2) + "s", meta.getColumnName(i + 1));
                }
                System.out.println();
                for (int i = 0; i < columnas; i++) {
                    System.out.print("-".repeat(anchos[i] + 2));
                }
                System.out.println();

                for (int fila = 0; fila < filasLeidas; fila++) {
                    for (int col = 0; col < columnas; col++) {
                        System.out.printf("%-" + (anchos[col] + 2) + "s", datos[fila][col]);
                    }
                    System.out.println();
                }

                System.out.println("\n[N] Siguiente | [P] Anterior | [S] Salir");
                String opcion = teclado.nextLine();

                switch (opcion.toUpperCase()) {
                    case "N":
                        offset += limit;
                        break;
                    case "P":
                        if (offset >= limit) offset -= limit;
                        else System.out.println("Ya estás en la primera página.");
                        break;
                    case "S":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar: " + e.getMessage());
            }
        }

        System.out.println("\nSaliendo de la paginación...");
    }
    public void consultarTablaAutores(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }
        int offset = 0;
        int limit = 5;
        String sql = "SELECT * FROM autores ORDER BY id LIMIT ? OFFSET ?";

        boolean salir = false;
        while (!salir) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, limit);
                pstmt.setInt(2, offset);

                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("\nNo hay más datos. Volverás a la página anterior si pulsas [P]:");
                    String opcion = teclado.nextLine();
                    if (opcion.equalsIgnoreCase("P") && offset >= limit) {
                        offset -= limit;
                    }
                    rs.close();
                    pstmt.close();
                    continue;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                String[][] datos = new String[limit][columnas];
                int filasLeidas = 0;
                int[] anchos = new int[columnas];
                for (int i = 0; i < columnas; i++) {
                    anchos[i] = meta.getColumnName(i + 1).length();
                }

                while (rs.next()) {
                    for (int i = 0; i < columnas; i++) {
                        String valor = rs.getString(i + 1);
                        if (valor == null) valor = "NULL";
                        datos[filasLeidas][i] = valor;
                        if (valor.length() > anchos[i]) anchos[i] = valor.length();
                    }
                    filasLeidas++;
                }

                rs.close();
                pstmt.close();

                System.out.println("\n--- Página " + (offset / limit + 1) + " ---");

                for (int i = 0; i < columnas; i++) {
                    System.out.printf("%-" + (anchos[i] + 2) + "s", meta.getColumnName(i + 1));
                }
                System.out.println();
                for (int i = 0; i < columnas; i++) {
                    System.out.print("-".repeat(anchos[i] + 2));
                }
                System.out.println();

                for (int fila = 0; fila < filasLeidas; fila++) {
                    for (int col = 0; col < columnas; col++) {
                        System.out.printf("%-" + (anchos[col] + 2) + "s", datos[fila][col]);
                    }
                    System.out.println();
                }

                System.out.println("\n[N] Siguiente | [P] Anterior | [S] Salir");
                String opcion = teclado.nextLine();

                switch (opcion.toUpperCase()) {
                    case "N":
                        offset += limit;
                        break;
                    case "P":
                        if (offset >= limit) offset -= limit;
                        else System.out.println("Ya estás en la primera página.");
                        break;
                    case "S":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar: " + e.getMessage());
            }
        }

        System.out.println("\nSaliendo de la paginación...");
    }
    public void consultarTablaPenalizaciones(Connection conn) {
        if (conn == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        Scanner teclado = new Scanner(System.in);
        int offset = 0;
        int limit = 5;
        String sql = "SELECT * FROM penalizaciones ORDER BY codigo LIMIT ? OFFSET ?";

        boolean salir = false;
        while (!salir) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, limit);
                pstmt.setInt(2, offset);

                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("\nNo hay más datos. Volverás a la página anterior si pulsas [P]:");
                    String opcion = teclado.nextLine();
                    if (opcion.equalsIgnoreCase("P") && offset >= limit) {
                        offset -= limit;
                    }
                    rs.close();
                    pstmt.close();
                    continue;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                String[][] datos = new String[limit][columnas];
                int filasLeidas = 0;
                int[] anchos = new int[columnas];
                for (int i = 0; i < columnas; i++) {
                    anchos[i] = meta.getColumnName(i + 1).length();
                }

                while (rs.next()) {
                    for (int i = 0; i < columnas; i++) {
                        String valor = rs.getString(i + 1);
                        if (valor == null) valor = "NULL";
                        datos[filasLeidas][i] = valor;
                        if (valor.length() > anchos[i]) anchos[i] = valor.length();
                    }
                    filasLeidas++;
                }

                rs.close();
                pstmt.close();

                System.out.println("\n--- Página " + (offset / limit + 1) + " ---");

                for (int i = 0; i < columnas; i++) {
                    System.out.printf("%-" + (anchos[i] + 2) + "s", meta.getColumnName(i + 1));
                }
                System.out.println();
                for (int i = 0; i < columnas; i++) {
                    System.out.print("-".repeat(anchos[i] + 2));
                }
                System.out.println();

                for (int fila = 0; fila < filasLeidas; fila++) {
                    for (int col = 0; col < columnas; col++) {
                        System.out.printf("%-" + (anchos[col] + 2) + "s", datos[fila][col]);
                    }
                    System.out.println();
                }

                System.out.println("\n[N] Siguiente | [P] Anterior | [S] Salir");
                String opcion = teclado.nextLine();

                switch (opcion.toUpperCase()) {
                    case "N":
                        offset += limit;
                        break;
                    case "P":
                        if (offset >= limit) offset -= limit;
                        else System.out.println("Ya estás en la primera página.");
                        break;
                    case "S":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar: " + e.getMessage());
            }
        }
        teclado.close();
        System.out.println("\nSaliendo de la paginación...");
    }
}


