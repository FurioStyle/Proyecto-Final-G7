package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelAdmin extends JPanel {
    public PanelAdmin() {
        setLayout(new BorderLayout());
        add(new JLabel("Bienvenido Administrador", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
