package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelObservador extends JPanel {
    public PanelObservador() {
        setLayout(new BorderLayout());
        add(new JLabel("Bienvenido Observador", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
