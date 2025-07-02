package GUI;

import Logica.CrearTorneo;
import Logica.TiposTorneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelAdmin extends JPanel {
    private JButton bCrear;
    private JButton bActualizar;
    private JButton bVolver;
    private ArrayList<Torneo> enCurso;
    private ArrayList<Torneo> finalizados;
    private DefaultListModel<Torneo> modeloEnCurso;
    private DefaultListModel<Torneo> modeloFinalizados;

    private JList<Torneo> listaEnCurso;
    private JList<Torneo> listaFinalizados;

    public PanelAdmin(VentanaPrincipal ventana) {
        setLayout(new BorderLayout());
        enCurso = new ArrayList<>();
        finalizados = new ArrayList<>();

        modeloEnCurso = new DefaultListModel<>();
        modeloFinalizados = new DefaultListModel<>();

        listaEnCurso = new JList<>(modeloEnCurso);
        listaFinalizados = new JList<>(modeloFinalizados);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        bCrear = new JButton("Crear");
        bActualizar = new JButton("Actualizar");
        bVolver = new JButton("Volver");

        bCrear.addActionListener(e ->
            mostrarDialogoCreacion()
        );

        bActualizar.addActionListener(e -> {});

        bVolver.addActionListener(e ->
            ventana.mostrarPanel("inicio")
        );

        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(bCrear);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(bActualizar);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(bVolver);
        panelBotones.add(Box.createVerticalStrut(10));

        JPanel panelLista = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 filas, 1 columna, con espacio vertical de 10

        JPanel panelEnCurso = new JPanel(new BorderLayout());
        panelEnCurso.setBorder(BorderFactory.createTitledBorder("Torneos En Curso"));
        panelEnCurso.add(new JScrollPane(listaEnCurso), BorderLayout.CENTER);

        JPanel panelFinalizado = new JPanel(new BorderLayout());
        panelFinalizado.setBorder(BorderFactory.createTitledBorder("Torneos Finalizados"));
        panelFinalizado.add(new JScrollPane(listaFinalizados), BorderLayout.CENTER);

        panelLista.add(panelEnCurso);
        panelLista.add(panelFinalizado);

        add(panelBotones, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
    }

    private void mostrarDialogoCreacion() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this),
                "Crear Torneo", Dialog.ModalityType.APPLICATION_MODAL);

        JPanel cont = new JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cont.add(new JLabel("Tipo de torneo:"));
        ButtonGroup grupoTipo = new ButtonGroup();
        JRadioButton rbBracket = new JRadioButton("Bracket");
        JRadioButton rbLiguilla = new JRadioButton("Liguilla");
        JRadioButton rbDobleElim = new JRadioButton("Doble Eliminación");
        grupoTipo.add(rbBracket);
        grupoTipo.add(rbLiguilla);
        grupoTipo.add(rbDobleElim);
        cont.add(rbBracket);
        cont.add(rbLiguilla);
        cont.add(rbDobleElim);

        cont.add(new JLabel("Número de participantes:"));
        ButtonGroup grupoPart = new ButtonGroup();
        JRadioButton rb8 = new JRadioButton("8");
        JRadioButton rb16 = new JRadioButton("16");
        JRadioButton rb32 = new JRadioButton("32");
        grupoPart.add(rb8);
        grupoPart.add(rb16);
        grupoPart.add(rb32);
        cont.add(rb8);
        cont.add(rb16);
        cont.add(rb32);

        cont.add(Box.createVerticalStrut(10));
        cont.add(new JLabel("Nombre del torneo:"));
        JTextField txtNombre = new JTextField(20);
        cont.add(txtNombre);

        cont.add(Box.createVerticalStrut(10));
        JButton btnAceptar = new JButton("Aceptar");
        cont.add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty() || grupoTipo.getSelection() == null || grupoPart.getSelection() == null) {
                JOptionPane.showMessageDialog(dialog, "Completa todos los campos.",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int tipo = rbBracket.isSelected() ? 1 : rbLiguilla.isSelected() ? 2 : 3;
            int participantes = rb8.isSelected() ? 8 : rb16.isSelected() ? 16 : 32;

            CrearTorneo creador = new CrearTorneo(tipo, participantes, nombre);
            Torneo nuevo = creador.getTorneo();

            enCurso.add(nuevo);
            modeloEnCurso.addElement(nuevo);

            dialog.dispose();
        });

        dialog.setContentPane(cont);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}

