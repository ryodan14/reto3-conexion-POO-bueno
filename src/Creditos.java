import javax.swing.*;
import java.awt.*;

public class Creditos extends JFrame {

    public Creditos() {
        setTitle("Créditos sobre imagen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar ventana

        // Cargar imagen de fondo
        ImageIcon fondoIcon = new ImageIcon("imagen/dark_logo.jpg"); // ← cambia esta ruta

        // Etiqueta para mostrar la imagen (fondo)
        JLabel fondo = new JLabel(fondoIcon);
        fondo.setBounds(0, 0, fondoIcon.getIconWidth(), fondoIcon.getIconHeight());

        // Panel para poner los créditos encima
        JPanel panelTexto = new JPanel();
        panelTexto.setOpaque(false); // Transparente
        panelTexto.setLayout(new BorderLayout());

        JLabel creditos = new JLabel(
            "<html><center><h2 style='color:white;'>✨ CRÉDITOS ✨</h2>" +
            "<span style='color:white;'>Asier Uriarte<br>" +
            "Diego Larrabeiti<br>" +
            "Eneko Martín<br>" +
            "Daniel Moruelo<br>" +
            "Johnattan cañete</span></center></html>"
        );
        creditos.setHorizontalAlignment(SwingConstants.CENTER);
        creditos.setVerticalAlignment(SwingConstants.CENTER);
        creditos.setFont(new Font("Serif", Font.BOLD, 18));
        panelTexto.add(creditos, BorderLayout.CENTER);

        // Usar un JLayeredPane para apilar imagen y texto
        JLayeredPane layeredPane = new JLayeredPane();
        fondo.setSize(800, 600);         // Asegúrate de que la imagen ocupe todo
        panelTexto.setBounds(0, 0, 800, 600);

        layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);       // Fondo en capa baja
        layeredPane.add(panelTexto, JLayeredPane.PALETTE_LAYER);  // Texto encima

        setContentPane(layeredPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Creditos().setVisible(true);
        });
    }
}
