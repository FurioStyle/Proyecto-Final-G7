package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Decidí implementar singleton en la ventana principal para que no se creen distintas
 * instancias de la misma ventana
 */
public class VentanaPrincipal extends JFrame {
    private static VentanaPrincipal instancia; // Uso de Singleton
    private CardLayout cardLayout;
    private JPanel pPrincipal;
    private PanelObservador panelObservador;

    private VentanaPrincipal() {
        setTitle("Administrador de Torneos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        pPrincipal = new JPanel(cardLayout);

        PanelInicio panelInicio = new PanelInicio(this);
        PanelAdmin panelAdmin = new PanelAdmin(this);
        panelObservador = new PanelObservador(this, panelAdmin);

        pPrincipal.add(panelInicio, "inicio");
        pPrincipal.add(panelAdmin, "admin");
        pPrincipal.add(panelObservador, "observador");

        add(pPrincipal);
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
        cardLayout.show(pPrincipal, nombre);
    }

    public void actualizarListas(){
        panelObservador.actualizarListas();
    }
}
