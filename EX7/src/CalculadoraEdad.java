import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculadoraEdad extends JFrame {
    private JFormattedTextField dateTextField;
    private JButton calculateButton;

    public CalculadoraEdad() {
        setTitle("Calculadora de Edad");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el JFormattedTextField
        MaskFormatter dateFormatter;
        try {
            dateFormatter = new MaskFormatter("##-##-####");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        dateFormatter.setPlaceholderCharacter('_');
        dateTextField = new JFormattedTextField(dateFormatter);
        dateTextField.setColumns(10);

        // Crear el botón de cálculo
        calculateButton = new JButton("Calcular Edad");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEdad();
            }
        });

        // Añadir componentes al panel principal
        mainPanel.add(dateTextField, BorderLayout.NORTH);
        mainPanel.add(calculateButton, BorderLayout.CENTER);

        // Añadir el panel principal al JFrame
        add(mainPanel);
    }

    private void calcularEdad() {
        String dateString = dateTextField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date birthDate = dateFormat.parse(dateString);
            Calendar today = Calendar.getInstance();
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthDate);

            int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            JOptionPane.showMessageDialog(this, "La edad es: " + age + " años", "Edad Calculada", JOptionPane.INFORMATION_MESSAGE);

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Introduce la fecha en formato dd-MM-yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraEdad();
            }
        });
    }
}
