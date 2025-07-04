package GUI;

import Logica.TiposTorneo.Torneo;

import javax.swing.*;
import java.awt.*;

public class PanelObservador extends JPanel {
    private JButton bVer;
    private JButton bActualizar;
    private JButton bVolver;

    private DefaultListModel<Torneo> modeloEnCurso;
    private DefaultListModel<Torneo> modeloFinalizados;

    private JList<Torneo> listaEnCurso;
    private JList<Torneo> listaFinalizados;

    private PanelAdmin panelAdmin;

    public PanelObservador(VentanaPrincipal ventana, PanelAdmin panelAdmin) {
        this.panelAdmin = panelAdmin;

        setLayout(new BorderLayout());

        modeloEnCurso = new DefaultListModel<>();
        modeloFinalizados = new DefaultListModel<>();

        listaEnCurso = new JList<>(modeloEnCurso);
        listaFinalizados = new JList<>(modeloFinalizados);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        bVer = new JButton("Ver Torneo");
        bActualizar = new JButton("Actualizar");
        bVolver = new JButton("Volver");

        bVer.addActionListener(e -> {
            Torneo seleccionado = listaEnCurso.getSelectedValue();
            if (seleccionado != null) {
                JOptionPane.showMessageDialog(this, "Seleccionaste: " + seleccionado.getNombre());
            }
        });

        bActualizar.addActionListener(e -> {
            actualizarListas();
        });

        bVolver.addActionListener(e -> {
            ventana.mostrarPanel("inicio");
        });

        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(bVer);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(bActualizar);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(bVolver);

        JPanel panelLista = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel panelLista1 = new JPanel(new BorderLayout());
        panelLista1.setBorder(BorderFactory.createTitledBorder("Torneos En Curso"));
        panelLista1.add(new JScrollPane(listaEnCurso), BorderLayout.CENTER);

        JPanel panelLista2 = new JPanel(new BorderLayout());
        panelLista2.setBorder(BorderFactory.createTitledBorder("Torneos Finalizados"));
        panelLista2.add(new JScrollPane(listaFinalizados), BorderLayout.CENTER);

        panelLista.add(panelLista1);
        panelLista.add(panelLista2);

        add(panelBotones, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
    }

    public void actualizarListas() {
        modeloEnCurso.clear();
        for (Torneo t : panelAdmin.getEnCurso()) {
            modeloEnCurso.addElement(t);
        }

        modeloFinalizados.clear();
        for (Torneo t : panelAdmin.getFinalizados()) {
            modeloFinalizados.addElement(t);
        }
    }
}
