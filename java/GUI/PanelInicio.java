package GUI;

import javax.swing.*;

public class PanelInicio extends JPanel {
    public PanelInicio(VentanaPrincipal frame) {
        setLayout(null);

        JLabel titulo = new JLabel("Seleccione su acción", SwingConstants.CENTER);
        titulo.setBounds(200, 150, 300, 30);
        add(titulo);

        int buttonWidth = 120;
        int buttonHeight = 70;
        int spacing = 20;
        int totalWidth = buttonWidth * 2 + spacing;
        int startX = (700 - totalWidth) / 2;
        int y = 220;

        JButton admin = new JButton("Administrador");
        JButton observador = new JButton("Observador");

        admin.setBounds(startX, y, buttonWidth, buttonHeight);
        observador.setBounds(startX + buttonWidth + spacing, y, buttonWidth, buttonHeight);

        add(admin);
        add(observador);

        admin.addActionListener(e -> frame.mostrarPanel("admin"));
        observador.addActionListener(e -> frame.mostrarPanel("observador"));
    }
}
