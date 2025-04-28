import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PantallaCreditos extends JFrame {

    private Clip clip;

    public PantallaCreditos() {
        setTitle("D4RK 3NT3RPR1S3");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        // Cambia el icono
        ImageIcon iconoVentana = new ImageIcon("imagen/dark_logo.jpg");
        setIconImage(iconoVentana.getImage());

        // Usamos GridBagLayout para centrar todo en la ventana
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Texto centrado en JLabel (con HTML)
        JLabel textoCreditos = new JLabel("<html><div style='text-align: center;'>"
                + "<h1 style='color:white; width: 400px; font-size: 30px;'>🥇​ Créditos 🥇​</h1>"
                + "<p style='color:white;font-size: 10px;'>Asier Uriarte​</p>"
                + "<p style='color:white;font-size: 10px;'>Diego Larrabeiti​</p>"
                + "<p style='color:white;font-size: 10px;'>Eneko Martín</p>"
                + "<p style='color:white;font-size: 10px;'>Daniel Mourelo​</p>"
                + "<p style='color:white;font-size: 10px;'>Johnattan Cañete</p>"
                + "</div></html>");
        textoCreditos.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir texto
        panel.add(textoCreditos, gbc);

        // Imagen (abajo del texto)
        gbc.gridy = 1;
        ImageIcon icono = new ImageIcon("imagen/dark_logo.jpg");

        // Escalado opcional de imagen si es muy grande
        Image imagenEscalada = icono.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(imagenEscalada));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(imagenLabel, gbc);

        add(panel);

        // Iniciar música
        reproducirMusica("imagen/musica.wav");
    }

    private void reproducirMusica(String rutaArchivo) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(rutaArchivo));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Para que se repita la música
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarCreditos() {
        SwingUtilities.invokeLater(() -> {
            PantallaCreditos ventana = new PantallaCreditos();
            ventana.setVisible(true);
            ventana.setExtendedState(JFrame.NORMAL);
            ventana.toFront();
            ventana.requestFocus();
        });
    }

/*
    public PantallaCreditos() {
        setTitle("D4RK 3NT3RPR1S3");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

         // Cambia el icono
        ImageIcon iconoVentana = new ImageIcon("imagen/dark_logo.jpg");
        setIconImage(iconoVentana.getImage());     

        // Usamos GridBagLayout para centrar todo en la ventana
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Texto centrado en JLabel (con HTML)
        JLabel textoCreditos = new JLabel("<html><div style='text-align: center;'>"
                + "<h1 style='color:white; width: 400px; font-size: 30px;'>🥇​ Créditos 🥇​</h1>"
                + "<p style='color:white;font-size: 10px;'>Asier Uriarte​</p>"
                + "<p style='color:white;font-size: 10px;'>Diego Larrabeiti​</p>"
                + "<p style='color:white;font-size: 10px;'>Eneko Martín</p>"
                + "<p style='color:white;font-size: 10px;'>Daniel Mourelo​</p>"
                + "<p style='color:white;font-size: 10px;'>Johnattan Cañete</p>"
                + "</div></html>");
        textoCreditos.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir texto
        panel.add(textoCreditos, gbc);

        // Imagen (abajo del texto)
        gbc.gridy = 1;
        ImageIcon icono = new ImageIcon("imagen/dark_logo.jpg");

        // Escalado opcional de imagen si es muy grande
        Image imagenEscalada = icono.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(imagenEscalada));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(imagenLabel, gbc);

        add(panel);
    }

    public static void mostrarCreditos() {
        SwingUtilities.invokeLater(() -> {
            PantallaCreditos ventana = new PantallaCreditos();
            ventana.setVisible(true);
            ventana.setExtendedState(JFrame.NORMAL);
            ventana.toFront();
            ventana.requestFocus();
        });
    }*/

    public static void main(String[] args) {
        mostrarCreditos();
    }
}