import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Update {
    public static void modificarSocios(Connection conn) {
        if (conn == null) {
            System.out.println( "No hay conexión");
            return;
        }
        int cont=0;
        Scanner sc = new Scanner(System.in);
        String sql = "UPDATE socios SET ";
        boolean ponerComa=false;
        String coma=", ";
        String id="";
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
            switch (decision) {
                case 1:
                    if(id==""){
                        System.out.println("\nIntroduce el id del socio a modificar:");
                        id = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="id="+id;
                    }else{
                        System.out.println("Ya pusiste el ID anteriormente");
                    }
                    break;

                case 2:
                    if(nombre==""){
                        System.out.println("Introduce el nuevo nombre del socio:");
                        nombre = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="nombre="+nombre;
                    }else{
                        System.out.println("Ya pusiste el nombre anteriormente");
                    }
                    break;

                case 3:
                    if (apellidos=="") {
                        System.out.println("Introduce los nuevos apellidos del socio:");
                        apellidos = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="apellidos="+apellidos;
                    } else {
                        System.out.println("Ya pusiste el/los apellidos anteriormente");
                    }
                    break;

                case 4:
                    if (direccion=="") {
                        System.out.println("Introduce la nueva direccion del socio:");
                        direccion = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="direccion="+direccion;
                    } else {
                        System.out.println("Ya pusiste la dirección anteriormente");
                    }
                    break;

                case 5:
                    if (telefono=="") {
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

                case 6:
                    if (email=="") {
                        System.out.println("Introduce el nuevo email del socio:");
                        email = sc.nextLine();

                        if (ponerComa) {
                            sql+=coma;
                        }
                        ponerComa=true;

                        sql+="email="+email;
                    } else {
                        System.out.println("Ya pusiste el email anteriormente");
                    }
                    break;

                case 7:
                    while (decision!=3) {
                        System.out.println("************************************************");
                        System.out.println("***  ¿Quieres aplicarlo a todos los campos?  ***");
                        System.out.println("************************************************");
                        System.out.println("    1-Si            2-No        3-Volver atras");
                        decision=sc.nextInt();
                        switch (decision) {
                            case 1:
                                //Se hace directamente el update sin el where
                                //TODO
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
                                    switch (decision) {
                                        case 1:
                                            if(idBuscar==""){
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
                                            if(nombreBuscar==""){
                                                System.out.println("Introduce el  nombre del socio a buscar:");
                                                nombreBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="nombre="+nombreBuscar;
                                            }else{
                                                System.out.println("Ya pusiste el nombre anteriormente");
                                            }
                                            break;

                                        case 3:
                                            if (apellidosBuscar=="") {
                                                System.out.println("Introduce los apellidos del socio a buscar:");
                                                apellidosBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="apellidos="+apellidosBuscar;
                                            } else {
                                                System.out.println("Ya pusiste el/los apellidos anteriormente");
                                            }
                                            break;

                                        case 4:
                                            if (direccionBuscar=="") {
                                                System.out.println("Introduce la direccion del socio a buscar:");
                                                direccionBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="direccion="+direccionBuscar;
                                            } else {
                                                System.out.println("Ya pusiste la dirección anteriormente");
                                            }
                                            break;

                                        case 5:
                                            if (telefonoBuscar=="") {
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
                                            if (emailBuscar=="") {
                                                System.out.println("Introduce el email del socio a buscar:");
                                                emailBuscar = sc.nextLine();

                                                if (ponerAnd) {
                                                    sql+=and;
                                                }
                                                ponerAnd=true;

                                                sql+="email="+emailBuscar;
                                            } else {
                                                System.out.println("Ya pusiste el email anteriormente");
                                            }
                                            break;

                                        case 7:
                                            System.out.println("Finalizando.....");
                                            break;
                                        default:
                                            System.out.println("Introduce una de las opciones dadas");
                                            break;
                                    }
                                    sql+=" ;";
                                    //Se hace el update
                                    //TODO
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
            
                case 8:
                    return;
                default:
                    System.out.println("Escoge una opción de las dadas");
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

