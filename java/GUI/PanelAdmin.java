package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelAdmin extends JPanel {
    private JButton btnCrearTorneo;
    private JButton btnActualizarTorneo;
    private JButton btnVolver;

    public PanelAdmin(VentanaPrincipal ventana) {
        setLayout(new BorderLayout());

        JPanel Botones = new JPanel();
        Botones.setLayout(new BoxLayout(Botones, BoxLayout.Y_AXIS));

        btnCrearTorneo = new JButton("Crear Torneo");
        btnActualizarTorneo = new JButton("Actualizar Torneo");
        btnVolver = new JButton("Volver");

        Botones.add(btnCrearTorneo);
        Botones.add(Box.createVerticalStrut(10));
        Botones.add(btnActualizarTorneo);
        Botones.add(Box.createVerticalStrut(10));
        Botones.add(btnVolver);
        btnVolver.addActionListener(e -> ventana.mostrarPanel("inicio"));
        Botones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(Botones, BorderLayout.WEST);
    }
}
