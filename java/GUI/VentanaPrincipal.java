package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * decidi implementar singleton en la ventana principal para que no se creen distintas
 * instancias de la misma ventana
 */
public class VentanaPrincipal extends JFrame {
    private static VentanaPrincipal instancia; // Uso de Singleton
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private VentanaPrincipal() {
        setTitle("Administrador de Torneos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        // Inicializar CardLayout y panel contenedor
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los tres paneles
        PanelInicio panelInicio = new PanelInicio(this);
        PanelAdmin panelAdmin = new PanelAdmin(this);
        PanelObservador panelObservador = new PanelObservador(this);

        // Agregar los paneles al CardLayout
        mainPanel.add(panelInicio, "inicio");
        mainPanel.add(panelAdmin, "admin");
        mainPanel.add(panelObservador, "observador");

        add(mainPanel);
        setVisible(true);
    }
        // Singleton
    public static VentanaPrincipal getInstancia(){
        if(instancia == null){
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }

    // Cambiar de panel
    public void mostrarPanel(String nombre) {
        cardLayout.show(mainPanel, nombre);
    }
}
