import java.sql.*;
import java.util.*;

public class Update {
    public static void modificarSocios(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
        //Idea original
        /*
        int cont=0;
        Scanner sc = new Scanner(System.in);
        String sql = "UPDATE socios SET ";
        boolean ponerComa=false;
        String coma=", ";
        String nombre="";
        String apellidos="";
        String direccion="";
        String telefono="";
        String email="";
        int decision=0;
        while (decision!=8) {
        System.out.println("******************************************************");
        System.out.println("*****     ¿Que desea cambiar?     "+cont+" cambio/s ****");
        System.out.println("******************************************************");
        System.out.println("    1-ID                        4-Dirección");
        System.out.println("    2-Nombre                    5-Telefono");
        System.out.println("    3-Apellidos                 6-Email");
        System.out.println("    7-Siguiente paso            8-Volver al inicio");
        decision=sc.nextInt();
        sc.nextLine();
            switch (decision) {
                case 1:
                    if(nombre.equals("")){
                        System.out.println("Introduce el nuevo nombre del socio:");
                        nombre = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="nombre='"+nombre+"'";
                    }else{
                        System.out.println("Ya pusiste el nombre anteriormente");
                    }
                    break;

                case 2:
                    if (apellidos.equals("")) {
                        System.out.println("Introduce los nuevos apellidos del socio:");
                        apellidos = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="apellidos='"+apellidos+"'";
                    } else {
                        System.out.println("Ya pusiste el/los apellidos anteriormente");
                    }
                    break;

                case 3:
                    if (direccion.equals("")) {
                        System.out.println("Introduce la nueva direccion del socio:");
                        direccion = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="direccion='"+direccion+"'";
                    } else {
                        System.out.println("Ya pusiste la dirección anteriormente");
                    }
                    break;

                case 4:
                    if (telefono.equals("")) {
                        System.out.println("Introduce el nuevo telefono del socio:");
                        telefono = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="telefono="+telefono;
                    } else {
                        System.out.println("Ya pusiste el teléfono anteriormente");
                    }
                    break;

                case 5:
                    if (email.equals("")) {
                        System.out.println("Introduce el nuevo email del socio:");
                        email = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="email='"+email+"'";
                    } else {
                        System.out.println("Ya pusiste el email anteriormente");
                    }
                    break;

                case 6:
                    while (decision!=3) {
                        System.out.println("************************************************");
                        System.out.println("***  ¿Quieres aplicarlo a todos los campos?  ***");
                        System.out.println("************************************************");
                        System.out.println("    1-Si            2-No        3-Volver atras");
                        decision=sc.nextInt();
                        switch (decision) {
                            case 1:
                                //Se hace directamente el update sin el where
                                sql+=";";
                                try (Statement stmt = conn.createStatement()) {
                                    int filas = stmt.executeUpdate(sql);
                                    System.out.println("Se actualizaron " + filas + " filas.");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 2:
                                sql+=" WHERE ";
                                String and=" AND ";
                                boolean ponerAnd=false;
                                String idBuscar="";
                                String nombreBuscar="";
                                String apellidosBuscar="";
                                String direccionBuscar="";
                                String telefonoBuscar="";
                                String emailBuscar="";
                                while (decision!=7) {
                                    System.out.println("************************************************");
                                    System.out.println("***             ¿A quien buscas?             ***");
                                    System.out.println("************************************************");
                                    System.out.println("   1-Por ID                      4-Por dirección");
                                    System.out.println("   2-Por nombre                  5-Por telefono");
                                    System.out.println("   3-Por apellidos               6-Por email");
                                    System.out.println("                                 7-Finalizar");
                                    decision=sc.nextInt();
                                    sc.nextLine();
                                    switch (decision) {
                                        case 1:
                                            if(idBuscar.equals("")){
                                                System.out.println("\nIntroduce el id del socio a buscar:");
                                                idBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="id="+idBuscar;
                                            }else{
                                                System.out.println("Ya pusiste el ID anteriormente");
                                            }
                                            break;
                                    
                                        case 2:
                                            if(nombreBuscar.equals("")){
                                                System.out.println("Introduce el  nombre del socio a buscar:");
                                                nombreBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="nombre='"+nombreBuscar+"'";
                                            }else{
                                                System.out.println("Ya pusiste el nombre anteriormente");
                                            }
                                            break;

                                        case 3:
                                            if (apellidosBuscar.equals("")) {
                                                System.out.println("Introduce los apellidos del socio a buscar:");
                                                apellidosBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="apellidos='"+apellidosBuscar+"'";
                                            } else {
                                                System.out.println("Ya pusiste el/los apellidos anteriormente");
                                            }
                                            break;

                                        case 4:
                                            if (direccionBuscar.equals("")) {
                                                System.out.println("Introduce la direccion del socio a buscar:");
                                                direccionBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="direccion='"+direccionBuscar+"'";
                                            } else {
                                                System.out.println("Ya pusiste la dirección anteriormente");
                                            }
                                            break;

                                        case 5:
                                            if (telefonoBuscar.equals("")) {
                                                System.out.println("Introduce el telefono del socio a buscar:");
                                                telefonoBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="telefono="+telefonoBuscar;
                                            } else {
                                                System.out.println("Ya pusiste el teléfono anteriormente");
                                            }
                                            break;

                                        case 6:
                                            if (emailBuscar.equals("")) {
                                                System.out.println("Introduce el email del socio a buscar:");
                                                emailBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="email='"+emailBuscar+"'";
                                            } else {
                                                System.out.println("Ya pusiste el email anteriormente");
                                            }
                                            break;

                                        case 7:
                                            System.out.println("Finalizando.....");
                                            sql+=";";
                                            try (Statement stmt = conn.createStatement()) {
                                                int filas = stmt.executeUpdate(sql);
                                                System.out.println("Se actualizaron " + filas + " filas.");
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        default:
                                            System.out.println("Introduce una de las opciones dadas");
                                            break;
                                    }
                                }
                                
                                break;

                            case 3:
                                System.out.println("Volviendo atras... ");
                                break;
                        
                            default:
                                break;
                        }
                    }
                    
                    return;
            
                case 7:
                    return;
                default:
                    System.out.println("Escoge una opción de las dadas");
                    break;
            }
        } */
        
        Scanner sc = new Scanner(System.in);
        StringBuilder setClause = new StringBuilder();
        StringBuilder whereClause = new StringBuilder();
        boolean setHasValue = false;
        int decision = 0;

        // Recogida de campos a modificar
        while (decision != 7 && decision != 8) {
            System.out.println("\n******************************************************");
            System.out.println("*****           ¿Qué deseas cambiar?             *****");
            System.out.println("******************************************************");
            System.out.println("       1-Nombre                 2-Apellidos");
            System.out.println("       3-Dirección              4-Teléfono");
            System.out.println("       5-Email                  6-Siguiente paso");
            System.out.println("       7-Cancelar                  ");

            decision = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (decision) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine().toUpperCase();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("nombre='").append(nombre).append("'");
                    setHasValue = true;
                    break;
                case 2:
                    System.out.print("Nuevos apellidos: ");
                    String apellidos = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("apellidos='").append(apellidos).append("'");
                    setHasValue = true;
                    break;
                case 3:
                    System.out.print("Nueva dirección: ");
                    String direccion = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("direccion='").append(direccion).append("'");
                    setHasValue = true;
                    break;
                case 4:
                    System.out.print("Nuevo teléfono: ");
                    String telefono = sc.nextLine();
                    while (Function.validarTelf(telefono)!=true) {
                        System.out.println("El télefono no es válido: ");
                        telefono = sc.nextLine();
                    }
                    if (setHasValue) setClause.append(", ");
                    setClause.append("telefono=").append(telefono);
                    setHasValue = true;
                    break;
                case 5:
                    System.out.print("Nuevo email: ");
                    String email = sc.nextLine();
                    while (Function.validarCorreo(email)!=true) {
                        System.out.println("El correo no es válido: ");
                        email = sc.nextLine();
                    }
                    if (setHasValue) setClause.append(", ");
                    setClause.append("email='").append(email).append("'");
                    setHasValue = true;
                    break;
                case 6:
                    // ¿Aplicar a todos o a uno solo?
                    System.out.println("\n************************************************");
                    System.out.println("***   ¿Aplicar cambios a todos los socios?   ***");
                    System.out.println("************************************************");
                    System.out.println("        1-Sí     2-No (filtrar con condiciones)");
                    int aplicarTodos = sc.nextInt();
                    sc.nextLine();

                    if (aplicarTodos == 2) {
                        boolean hasCond = false;
                        while (true) {
                            System.out.println("\n******************************************************");
                            System.out.println("***         Condiciones para buscar socios         ***");
                            System.out.println("******************************************************");
                            System.out.println("          1-ID                  2-Nombre");
                            System.out.println("          3-Apellidos            4-Dirección");
                            System.out.println("          5-Teléfono            6-Email   ");
                            System.out.println("          7-Finalizar condiciones");

                            int filtro = sc.nextInt();
                            sc.nextLine();

                            if (filtro == 7) break;

                            if (hasCond) whereClause.append(" AND ");

                            switch (filtro) {
                                case 1:
                                    System.out.print("ID: ");
                                    whereClause.append("id_socio=").append(sc.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Nombre: ");
                                    whereClause.append("nombre='").append(sc.nextLine()).append("'");
                                    break;
                                case 3:
                                    System.out.print("Apellidos: ");
                                    whereClause.append("apellidos='").append(sc.nextLine()).append("'");
                                    break;
                                case 4:
                                    System.out.print("Dirección: ");
                                    whereClause.append("direccion='").append(sc.nextLine()).append("'");
                                    break;
                                case 5:
                                    System.out.print("Teléfono: ");
                                    whereClause.append("tlfn=").append(sc.nextLine());
                                    break;
                                case 6:
                                    System.out.print("Email: ");
                                    whereClause.append("email='").append(sc.nextLine()).append("'");
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    hasCond = false;
                                    continue;
                            }
                            hasCond = true;
                        }
                    }

                    // Construir sentencia final
                    String sql = "UPDATE socios SET " + setClause.toString();
                    if (whereClause.length() > 0) {
                        sql += " WHERE " + whereClause.toString();
                    }
                    sql += ";";

                    // Ejecutar
                    try (Statement stmt = conn.createStatement()) {
                        int filas = stmt.executeUpdate(sql);
                        System.out.println("Se actualizaron " + filas + " fila(s).");
                        return;
                    } catch (SQLException e) {
                        System.out.println("Error al ejecutar el UPDATE:");
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.println("Cancelado.");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public static void modificarLibros(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;

        }
        Scanner sc = new Scanner(System.in);
        StringBuilder setClause = new StringBuilder();
        StringBuilder whereClause = new StringBuilder();
        boolean setHasValue = false;
        int decision = 0;

        // Recogida de campos a modificar
        while (decision != 7 && decision != 8) {
            System.out.println("\n******************************************************");
            System.out.println("*****           ¿Qué deseas cambiar?             *****");
            System.out.println("******************************************************");
            System.out.println("       1-Título                 2-ISBN");
            System.out.println("       3-Número de páginas      4-Año publicación");
            System.out.println("       5-Ejemplares             6-Siguiente paso");
            System.out.println("       7-Cancelar                  ");

            decision = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (decision) {
                case 1:
                    System.out.print("Nuevo título: ");
                    String titulo = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("titulo='").append(titulo).append("'");
                    setHasValue = true;
                    break;
                case 2:
                    System.out.print("Nuevos ISBN: ");
                    String isbn = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("isbn=").append(isbn);
                    setHasValue = true;
                    break;
                case 3:
                    System.out.print("Nueva número de páginas: ");
                    String numPag = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("numPags=").append(numPag);
                    setHasValue = true;
                    break;
                case 4:
                    System.out.print("Nuevo año de publicación: ");
                    String anioPub = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("anioPubli='").append(anioPub).append("'");
                    setHasValue = true;
                    break;
                case 5:
                    System.out.print("Nuevo ejemplar: ");
                    String ejemplar = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("ejemplares=").append(ejemplar);
                    setHasValue = true;
                    break;
                case 6:
                    // ¿Aplicar a todos o a uno solo?
                    System.out.println("\n************************************************");
                    System.out.println("***   ¿Aplicar cambios a todos los libros?   ***");
                    System.out.println("************************************************");
                    System.out.println("        1-Sí     2-No (filtrar con condiciones)");
                    int aplicarTodos = sc.nextInt();
                    sc.nextLine();

                    if (aplicarTodos == 2) {
                        boolean hasCond = false;
                        while (true) {
                            System.out.println("\n******************************************************");
                            System.out.println("***         Condiciones para buscar libros         ***");
                            System.out.println("******************************************************");
                            System.out.println("          1-ID                  2-Título");
                            System.out.println("          3-ISBN                4-Número páginas");
                            System.out.println("          5-Año publicación     6-Ejemplares   ");
                            System.out.println("          7-Finalizar condiciones");

                            int filtro = sc.nextInt();
                            sc.nextLine();

                            if (filtro == 7) break;

                            if (hasCond) whereClause.append(" AND ");

                            switch (filtro) {
                                case 1:
                                    System.out.print("ID: ");
                                    whereClause.append("id_libro=").append(sc.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Título: ");
                                    whereClause.append("titulo='").append(sc.nextLine()).append("'");
                                    break;
                                case 3:
                                    System.out.print("ISBN: ");
                                    whereClause.append("isbn=").append(sc.nextLine());
                                    break;
                                case 4:
                                    System.out.print("Número de páginas: ");
                                    whereClause.append("numPags=").append(sc.nextLine());
                                    break;
                                case 5:
                                    System.out.print("Año publicación: ");
                                    whereClause.append("anioPubli='").append(sc.nextLine()).append("'");
                                    break;
                                case 6:
                                    System.out.print("Ejemplar: ");
                                    whereClause.append("ejemplares=").append(sc.nextLine());
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    hasCond = false;
                                    continue;
                            }
                            hasCond = true;
                        }
                    }

                    // Construir sentencia final
                    String sql = "UPDATE libros SET " + setClause.toString();
                    if (whereClause.length() > 0) {
                        sql += " WHERE " + whereClause.toString();
                    }
                    sql += ";";

                    // Ejecutar
                    try (Statement stmt = conn.createStatement()) {
                        int filas = stmt.executeUpdate(sql);
                        System.out.println("Se actualizaron " + filas + " fila(s).");
                        return;
                    } catch (SQLException e) {
                        System.out.println("Error al ejecutar el UPDATE:");
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.println("Cancelado.");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }

    public static void modificarPrestamos(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
        Scanner sc = new Scanner(System.in);
        StringBuilder setClause = new StringBuilder();
        StringBuilder whereClause = new StringBuilder();
        boolean setHasValue = false;
        int decision = 0;

        // Recogida de campos a modificar
        while (decision != 7 && decision != 8) {
            System.out.println("\n******************************************************");
            System.out.println("*****           ¿Qué deseas cambiar?             *****");
            System.out.println("******************************************************");
            System.out.println("       1-Fecha de inicio        2-Fecha de devolución");
            System.out.println("       3-Entregado              4-ID Socio");
            System.out.println("       5-ID libro               6-Siguiente paso");
            System.out.println("       7-Cancelar                  ");

            decision = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (decision) {
                case 1:
                    System.out.print("Nueva fecha de inicio: ");
                    String fechaInicio = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("fecha_inicio='").append(fechaInicio).append("'");
                    setHasValue = true;
                    break;
                case 2:
                    System.out.print("Nueva fecha de devolución: ");
                    String fechaDev = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("fecha_devolucion='").append(fechaDev).append("'");
                    setHasValue = true;
                    break;
                case 3:
                    System.out.print("Nuevo si esta entregado o no: ");
                    String entregado = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("entregado='").append(entregado).append("'");
                    setHasValue = true;
                    break;
                case 4:
                    System.out.print("Nuevo Id socio: ");
                    String socio = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("socio=").append(socio);
                    setHasValue = true;
                    break;
                case 5:
                    System.out.print("Nuevo Id libro: ");
                    String libro = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("id_libro='").append(libro);
                    setHasValue = true;
                    break;
                case 6:
                    // ¿Aplicar a todos o a uno solo?
                    System.out.println("\n************************************************");
                    System.out.println("***  ¿Aplicar cambios a todos los préstamos? ***");
                    System.out.println("************************************************");
                    System.out.println("        1-Sí     2-No (filtrar con condiciones)");
                    int aplicarTodos = sc.nextInt();
                    sc.nextLine();

                    if (aplicarTodos == 2) {
                        boolean hasCond = false;
                        while (true) {
                            System.out.println("\n******************************************************");
                            System.out.println("***         Condiciones para buscar préstamos      ***");
                            System.out.println("******************************************************");
                            System.out.println("          1-ID                  2-Fecha de inicio");
                            System.out.println("          3-Fecha de devolución 4-Entregado");
                            System.out.println("          5-ID socio            6-ID libro   ");
                            System.out.println("          7-Finalizar condiciones");

                            int filtro = sc.nextInt();
                            sc.nextLine();

                            if (filtro == 7) break;

                            if (hasCond) whereClause.append(" AND ");

                            switch (filtro) {
                                case 1:
                                    System.out.print("ID: ");
                                    whereClause.append("codigo=").append(sc.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Fecha de inicio: ");
                                    whereClause.append("fecha_inicio='").append(sc.nextLine()).append("'");
                                    break;
                                case 3:
                                    System.out.print("Fecha de devolución: ");
                                    whereClause.append("fecha_devolucion='").append(sc.nextLine()).append("'");
                                    break;
                                case 4:
                                    System.out.print("Entregado: ");
                                    whereClause.append("entregado='").append(sc.nextLine()).append("'");
                                    break;
                                case 5:
                                    System.out.print("ID socio: ");
                                    whereClause.append("socio=").append(sc.nextLine());
                                    break;
                                case 6:
                                    System.out.print("Id libro: ");
                                    whereClause.append("id_libro='").append(sc.nextLine());
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    hasCond = false;
                                    continue;
                            }
                            hasCond = true;
                        }
                    }

                    // Construir sentencia final
                    String sql = "UPDATE prestamos SET " + setClause.toString();
                    if (whereClause.length() > 0) {
                        sql += " WHERE " + whereClause.toString();
                    }
                    sql += ";";

                    // Ejecutar
                    try (Statement stmt = conn.createStatement()) {
                        int filas = stmt.executeUpdate(sql);
                        System.out.println("Se actualizaron " + filas + " fila(s).");
                        return;
                    } catch (SQLException e) {
                        System.out.println("Error al ejecutar el UPDATE:");
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.println("Cancelado.");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }

    public static void modificarAutores(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
        Scanner sc = new Scanner(System.in);
        StringBuilder setClause = new StringBuilder();
        StringBuilder whereClause = new StringBuilder();
        boolean setHasValue = false;
        int decision = 0;

        // Recogida de campos a modificar
        while (decision != 7 && decision != 8) {
            System.out.println("\n******************************************************");
            System.out.println("*****           ¿Qué deseas cambiar?             *****");
            System.out.println("******************************************************");
            System.out.println("       1-Nombre                 2-Primer apellido");
            System.out.println("       3-Segundo apellido       4-Siguiente paso");
            System.out.println("       7-Cancelar");

            decision = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (decision) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("nombre='").append(nombre).append("'");
                    setHasValue = true;
                    break;
                case 2:
                    System.out.print("Nuevo primer apellido: ");
                    String apellidoUno = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("apellido1='").append(apellidoUno).append("'");
                    setHasValue = true;
                    break;
                case 3:
                    System.out.print("Nuevo segundo apellido: ");
                    String apellidoDos = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("apellido2='").append(apellidoDos).append("'");
                    setHasValue = true;
                    break;
                case 4:
                    // ¿Aplicar a todos o a uno solo?
                    System.out.println("\n************************************************");
                    System.out.println("***   ¿Aplicar cambios a todos los autores?  ***");
                    System.out.println("************************************************");
                    System.out.println("        1-Sí     2-No (filtrar con condiciones)");
                    int aplicarTodos = sc.nextInt();
                    sc.nextLine();

                    if (aplicarTodos == 2) {
                        boolean hasCond = false;
                        while (true) {
                            System.out.println("\n******************************************************");
                            System.out.println("***         Condiciones para buscar autores        ***");
                            System.out.println("******************************************************");
                            System.out.println("        1-ID                    2-Nombre");
                            System.out.println("        3-Primer apellido       4-Segundo apellido");
                            System.out.println("        5-Finalizar condiciones");

                            int filtro = sc.nextInt();
                            sc.nextLine();

                            if (filtro == 5) break;

                            if (hasCond) whereClause.append(" AND ");

                            switch (filtro) {
                                case 1:
                                    System.out.print("ID: ");
                                    whereClause.append("id=").append(sc.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Nombre: ");
                                    whereClause.append("nombre='").append(sc.nextLine()).append("'");
                                    break;
                                case 3:
                                    System.out.print("Primer apellido: ");
                                    whereClause.append("apellido1='").append(sc.nextLine()).append("'");
                                    break;
                                case 4:
                                    System.out.print("Segundo apellido: ");
                                    whereClause.append("apellido2='").append(sc.nextLine()).append("'");
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    hasCond = false;
                                    continue;
                            }
                            hasCond = true;
                        }
                    }

                    // Construir sentencia final
                    String sql = "UPDATE socios SET " + setClause.toString();
                    if (whereClause.length() > 0) {
                        sql += " WHERE " + whereClause.toString();
                    }
                    sql += ";";

                    // Ejecutar
                    try (Statement stmt = conn.createStatement()) {
                        int filas = stmt.executeUpdate(sql);
                        System.out.println("Se actualizaron " + filas + " fila(s).");
                        return;
                    } catch (SQLException e) {
                        System.out.println("Error al ejecutar el UPDATE:");
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Cancelado.");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }
}

