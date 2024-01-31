import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SliderDemo extends JFrame {
    private JSlider slider;
    private JTextField valueTextField;

    public SliderDemo() {
        setTitle("Slider Demo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el JSlider
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Crear el JTextField para mostrar el valor seleccionado
        valueTextField = new JTextField(5);
        valueTextField.setEditable(false);
        valueTextField.setHorizontalAlignment(JTextField.CENTER);

        // Configurar el ChangeListener para actualizar el valor en el JTextField
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = slider.getValue();
                valueTextField.setText(String.valueOf(sliderValue));
            }
        });

        // Añadir componentes al panel principal
        mainPanel.add(slider, BorderLayout.NORTH);
        mainPanel.add(valueTextField, BorderLayout.CENTER);

        // Añadir el panel principal al JFrame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SliderDemo();
            }
        });
    }
}
