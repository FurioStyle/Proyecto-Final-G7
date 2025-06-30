package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelObservador extends JPanel {
    private JButton btnActualizarTorneo;
    private JButton btnVolver;

    public PanelObservador(VentanaPrincipal ventana) {
        setLayout(new BorderLayout());

        JPanel Botones = new JPanel();
        Botones.setLayout(new BoxLayout(Botones, BoxLayout.Y_AXIS));

        btnActualizarTorneo = new JButton("Ver Torneo");
        btnVolver = new JButton("Volver");

        Botones.add(Box.createVerticalStrut(10));
        Botones.add(btnActualizarTorneo);
        Botones.add(Box.createVerticalStrut(10));
        Botones.add(btnVolver);
        btnVolver.addActionListener(e -> ventana.mostrarPanel("inicio"));
        Botones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(Botones, BorderLayout.WEST);
    }
}
