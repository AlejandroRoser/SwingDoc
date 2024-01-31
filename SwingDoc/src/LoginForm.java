import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginForm extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;

    public LoginForm() {
        // Set the icon image
        try {
            BufferedImage image = ImageIO.read(new File("./Icon/Monkey.jpg"));
            setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configurar el frame
        setTitle("Login Form");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.GREEN); // Establecer el color de fondo


        // Crear los componentes

        JLabel titleLabel = new JLabel("Welcome");
        titleLabel.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.GREEN);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel("User");
        userField = new JTextField(15);
        userPanel.add(userLabel);
        userPanel.add(userField);
        userPanel.setBackground(Color.GREEN);


        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(Color.GREEN);


        // Añadir espacio entre las etiquetas y los campos
        userPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton loginButton = new JButton("Log In");
        loginButton.setFocusable(false); // Desactivar el enfoque del botón

        // Cambiar el ActionListener para gestionar la creación de la nueva ventana
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               performLogin();
            }
        });
        JToggleButton colorToggleButton = new JToggleButton("Toggle Color");
        colorToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorToggleButton.isSelected()) {
                    getContentPane().setBackground(Color.BLUE);
                    userPanel.setBackground(Color.BLUE);
                    passwordPanel.setBackground(Color.BLUE);
                    titleLabel.setBackground(Color.BLUE);

                } else {
                    getContentPane().setBackground(Color.GREEN);
                    passwordPanel.setBackground(Color.GREEN);
                    userPanel.setBackground(Color.GREEN);
                    titleLabel.setBackground(Color.GREEN);

                }
            }
        });

        // Configurar el botón de color más pequeño
        colorToggleButton.setPreferredSize(new Dimension(100, 30));

        // Configurar el layout del frame
        setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridLayout(4, 1));
        contentPanel.add(titleLabel);
        contentPanel.add(userPanel);
        contentPanel.add(passwordPanel);
        contentPanel.add(loginButton);

        add(contentPanel, BorderLayout.CENTER);
        add(colorToggleButton, BorderLayout.SOUTH);

        // Configurar el orden de tabulación
        setFocusTraversalPolicy(new FocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container aContainer, Component aComponent) {
                if (aComponent.equals(userField))
                    return passwordField;
                else if (aComponent.equals(passwordField))
                    return loginButton;
                else if (aComponent.equals(loginButton))
                    return colorToggleButton;
                else
                    return userField;
            }

            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent) {
                if (aComponent.equals(passwordField))
                    return userField;
                else if (aComponent.equals(loginButton))
                    return passwordField;
                else if (aComponent.equals(colorToggleButton))
                    return loginButton;
                else
                    return colorToggleButton;
            }

            @Override
            public Component getFirstComponent(Container aContainer) {
                return userField;
            }

            @Override
            public Component getLastComponent(Container aContainer) {
                return colorToggleButton;
            }

            @Override
            public Component getDefaultComponent(Container aContainer) {
                return userField;
            }
        });

        // Añadir KeyListener al campo de contraseña
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // No necesitamos implementar este método
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Detectar la pulsación de Enter y realizar la acción correspondiente
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No necesitamos implementar este método
            }
        });

        setVisible(true);
    }

    // Método para realizar la acción de login
    private void performLogin() {
        String enteredUsername = userField.getText();
        String enteredPassword = new String(passwordField.getPassword());

        if (enteredUsername.equals("usuario") && enteredPassword.equals("password")) {
            // Credenciales correctas: Abrir la nueva ventana y cerrar la ventana actual
            Sesion sesionFrame = new Sesion();
            sesionFrame.setLocationRelativeTo(null); //
            sesionFrame.setVisible(true);
            dispose(); // Cerrar la ventana actual
        } else {
            // Credenciales incorrectas: Mostrar JOptionPane de error
            JOptionPane.showMessageDialog(LoginForm.this, "Login Failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm();
            }
        });
    }
}
