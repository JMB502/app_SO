import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class App_SO {
    private Queue<Auto> colaNormal = new LinkedList<>();
    private Queue<Auto> colaTelepeaje = new LinkedList<>();
    private int ordenNormal = 1;
    private int ordenTelepeaje = 1;

    private JFrame frame;
    private JPanel panel;
    private JTextField placaTextField;
    private JRadioButton normalRadioButton;
    private JRadioButton telepeajeRadioButton;
    private ButtonGroup radioButtonGroup;
    private JTextArea ordenTextArea;

    public App_SO() {
        frame = new JFrame("Sistema de Peaje");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel placaLabel = new JLabel("Placa del automóvil:");
        placaTextField = new JTextField(10);

        normalRadioButton = new JRadioButton("Normal");
        telepeajeRadioButton = new JRadioButton("Telepeaje");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(normalRadioButton);
        radioButtonGroup.add(telepeajeRadioButton);

        JButton registrarButton = new JButton("Registrar");

        ordenTextArea = new JTextArea(10, 30);
        ordenTextArea.setEditable(false);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = placaTextField.getText();
                boolean esTelepeaje = telepeajeRadioButton.isSelected();

                if (!placa.isEmpty()) {
                    Auto auto = new Auto(placa, esTelepeaje);

                    if (esTelepeaje) {
                        colaTelepeaje.offer(auto);
                        ordenTextArea.append("Telepeaje - Orden " + ordenTelepeaje + ": " + placa + "\n");
                        ordenTelepeaje++;
                    } else {
                        colaNormal.offer(auto);
                        ordenTextArea.append("Normal - Orden " + ordenNormal + ": " + placa + "\n");
                        ordenNormal++;
                    }

                    placaTextField.setText("");
                    radioButtonGroup.clearSelection();
                }
            }
        });

        panel.add(placaLabel);
        panel.add(placaTextField);
        panel.add(normalRadioButton);
        panel.add(telepeajeRadioButton);
        panel.add(registrarButton);
        panel.add(ordenTextArea);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App_SO();
            }
        });
    }
}

class Auto {
    private String placa;
    private boolean esTelepeaje;

    public Auto(String placa, boolean esTelepeaje) {
        this.placa = placa;
        this.esTelepeaje = esTelepeaje;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean esTelepeaje() {
        return esTelepeaje;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " (" + (esTelepeaje ? "Telepeaje" : "Normal") + ")";
    }
}

