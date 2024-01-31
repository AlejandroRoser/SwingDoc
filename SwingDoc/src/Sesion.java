import javax.swing.*;
import java.awt.*;

public class Sesion extends JFrame {
    public Sesion() {
        setTitle("Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo la ventana actual al hacer clic en la X
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("YOU ARE LOGGED IN!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(welcomeLabel, BorderLayout.CENTER);
    }
}