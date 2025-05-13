import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Update {
    public static void modificarSocios(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
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
            System.out.println("******************************************************");
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
                    String nombre = sc.nextLine();
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
                    if (setHasValue) setClause.append(", ");
                    setClause.append("telefono=").append(telefono);
                    setHasValue = true;
                    break;
                case 5:
                    System.out.print("Nuevo email: ");
                    String email = sc.nextLine();
                    if (setHasValue) setClause.append(", ");
                    setClause.append("email='").append(email).append("'");
                    setHasValue = true;
                    break;
                case 6:
                    // ¿Aplicar a todos o a uno solo?
                    System.out.println("************************************************");
                    System.out.println("***   ¿Aplicar cambios a todos los socios?   ***");
                    System.out.println("************************************************");
                    System.out.println("        1-Sí     2-No (filtrar con condiciones)");
                    int aplicarTodos = sc.nextInt();
                    sc.nextLine();

                    if (aplicarTodos == 2) {
                        boolean hasCond = false;
                        while (true) {
                            System.out.println("******************************************************");
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
    }

    public static void modificarPrestamos(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
    }

    public static void modificarAutores(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
    }
}

