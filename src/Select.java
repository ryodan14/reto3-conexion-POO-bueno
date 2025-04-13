import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Select {
    
    public static void fechaAlta(Connection conn) {
        
        protected String tiempo;
        String fecha_alta from socios;
        tiempo=(sysdate-fecha_alta);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la fecha de alta (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        LocalDateTime fechaAlta = LocalDateTime.parse(fecha + "T00:00:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = fechaAlta.format(formatter);
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "usuario", "contraseña");
            String sql = "INSERT INTO tu_tabla (fecha_alta) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, formattedDate);
            preparedStatement.executeUpdate();
            System.out.println("Fecha de alta insertada correctamente.");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
