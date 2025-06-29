package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public VentanaPrincipal() {
        setTitle("Administrador de Torneos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        // Inicializar CardLayout y panel contenedor
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los tres paneles
        PanelInicio panelInicio = new PanelInicio(this);
        PanelAdmin panelAdmin = new PanelAdmin();
        PanelObservador panelObservador = new PanelObservador();

        // Agregar los paneles al CardLayout
        mainPanel.add(panelInicio, "inicio");
        mainPanel.add(panelAdmin, "admin");
        mainPanel.add(panelObservador, "observador");

        add(mainPanel);
        setVisible(true);
    }

    // Cambiar de panel
    public void mostrarPanel(String nombre) {
        cardLayout.show(mainPanel, nombre);
    }
}
