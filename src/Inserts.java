public class Inserts {
        static String[] Nombres={"Daniela","María","Tristán","Lucas","Natalia","Llión","Mateo","Hugo","Damian","Saúl","Nayara","Sofía","Esteban","Thais","Selene","Liora","Rafael","Adriana","Irina"};
        static String[] Apellidos={"Gutierrez","Morales","Rastrojo","Fernández","Hernández","Esparza","Reyes","Ramírez","Lorente","Valverde","Estrada","Mendez","Villalba","Serrano","Montes","Esparza","Zamorano","Pereles","Sáenz"};
        static String[] Dirección={"AVENIDA DE LOS PINOS","TERRENOS DE LOS ARCOS","RESIDENCIAL LOS SAUCES","CALLE DE LOS OLIVOS","PASEO MARÍTIMO","AVENIDA DE LA CONSTITUCIÓN","AVENIDA CENTRAL","RONDA DEL BOSQUE","PASEO MARÍTIMO","PASEO DE LA ALAMEDA","CALLEJÓN DE LA LUNA","CALLE DE SAN MIGUEL","AVENIDA DE LA CONSTITUCIÓN","CALLEJÓN DEL ÁGUILA","CALLE DEL POZO","CALLE DE LOS NARANJOS","BARRIO DE SAN ANDRÉS","RONDA DE LAESPERANZA","URBANIZACIÓN EL MIRADOR"};
        static String[] tlfn={"723456789","653489572","600345172","645938124","697843562","697843258","679854312","687459123","625489712","639456789","534897652","612345789","698745123","698745632","674123985","645789321","654789632","698745213","645789654",};
        static String[] correo={"DANIELA.GUTIERREZ@GMAIL.COM","MARIA.MORALES@GMAIL.COM","TRISTAN.RASTROJO@GMAIL.COM","LUCAS.FERNANDEZ@GMAIL.COM","NATALIA.HERNANDEZ@GMAIL.COM","LLION.ESPARZA@GMAIL.COM","MATEO.REYES@GMAIL.COM","HUGO.RAMIREZ@GMAIL.COM","DAMIAN.LORENTE@GMAIL.COM","SAUL.VALVERDE@GMAIL.COM","NAYARA.ESTRADA@GMAIL.COM","SOFIA.MENDEZ@GMAIL.COM","ESTEBAN.VILLALBA@GMAIL.COM","THAIS.SERRANO@GMAIL.COM","SELENE.MONTES@GMAIL.COM","LIORA.ESPARZA@GMAIL.COM","RAFAEL.ZAMORANO@GMAIL.COM","ADRIANA.PERELES@GMAIL.COM","IRINA.SAENZ@GMAIL.COM"};
        static String [] usuario={"DANIELA.GUTIERREZ","MARIA.MORALES","TRISTAN.RASTROJO","LUCAS.FERNANDEZ","NATALIA.HERNANDEZ","LLION.ESPARZA","MATEO.REYES","HUGO.RAMIREZ","DAMIAN.LORENTE","SAUL.VALVERDE","NAYARA.ESTRADA","SOFIA.MENDEZ","ESTEBAN.VILLALBA","THAIS.SERRANO","SELENE.MONTES","LIORA.ESPARZA","RAFAEL.ZAMORANO","ADRIANA.PERELES","IRINA.SAENZ"};
        static String[] contraseña={"154724","2648","26943","81245","32455","51899","91834","51239","51308","50417","50942","32685","93105","51039","51402","51894","51789","97854","67895"};
        static String[] seguridad_social={"12340000000","34560000000","76540000000","vacio","vacio","vacio","41240000000","26540000000","vacio","12345678901","98765432102","23456789013","vacio","vacio","vacio","vacio","vacio","vacio","54321098780"};
    
        for (int i = 0; i < Nombres.length; i++) {
            String sql = String.format(
                "INSERT INTO usuarios (nombre, apellido, direccion, telefono, correo, usuario, contraseña, seguridad_social) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                Nombres[i], Apellidos[i], Dirección[i], tlfn[i], correo[i], usuario[i], contraseña[i], seguridad_social[i]
            );
        
            System.out.println(sql);
        }
    
        /*public static String generarApellidos(){
    int longi=aApellidos.length;
    int iAzar=(int)(Math.random()*longi);
    return(aApellidos[iAzar]);
    }*/
}

