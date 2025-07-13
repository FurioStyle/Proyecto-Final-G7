package GUI;

import Logica.CrearTorneo;
import Logica.Participantes.Participante;
import Logica.TiposTorneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelAdmin extends JPanel {
    private JButton bCrear;
    private JButton bAñadir;
    private JButton bGanadores;
    private JButton bVolver;
    private ArrayList<Torneo> enCurso;
    private ArrayList<Torneo> finalizados;
    private DefaultListModel<Torneo> modeloEnCurso;
    private DefaultListModel<Torneo> modeloFinalizados;

    private Torneo seleccionado;

    private JList<Torneo> listaEnCurso;
    private JList<Torneo> listaFinalizados;

    public PanelAdmin(VentanaPrincipal ventana) {
        setLayout(new BorderLayout());
        enCurso = new ArrayList<>();
        finalizados = new ArrayList<>();

        modeloEnCurso = new DefaultListModel<>();
        modeloFinalizados = new DefaultListModel<>();

        listaEnCurso = new JList<>(modeloEnCurso);

        listaEnCurso.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionado = listaEnCurso.getSelectedValue();
            }
        });

        listaFinalizados = new JList<>(modeloFinalizados);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        bCrear = new JButton("Crear");
        bAñadir = new JButton("Añadir Participante");
        bGanadores = new JButton("Seleccionar Ganadores");
        bVolver = new JButton("Volver");

        bCrear.addActionListener(e ->
            mostrarDialogoCreacion()
        );

        bAñadir.addActionListener(e -> {
                int np = seleccionado.getNumParticipantes();
                    if (np > seleccionado.getParticipantes().size()) {
                        mostrarDialogoAñadir();
                    }
                    else{
                        System.out.println("Torneo lleno");
                    }
                }
        );

        bGanadores.addActionListener(e -> {
            mostrarDialogoGanadores();
        });

        bVolver.addActionListener(e ->
            ventana.mostrarPanel("inicio")
        );

        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(bCrear);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(bAñadir);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(bGanadores);
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
        JRadioButton rbDobleElim = new JRadioButton("Doble Eliminación");
        grupoTipo.add(rbBracket);
        grupoTipo.add(rbDobleElim);
        cont.add(rbBracket);
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
                JOptionPane.showMessageDialog(dialog, "Completa todo.",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int tipo = rbBracket.isSelected() ? 1 : 2;
            int participantes = rb8.isSelected() ? 8 : rb16.isSelected() ? 16 : 32;

            // leve implementacion del patron factory
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
    
    public ArrayList<Torneo> getEnCurso(){
        return enCurso;
    }

    public ArrayList<Torneo> getFinalizados(){
        return finalizados;
    }

    private void mostrarDialogoAñadir() {
        if (seleccionado == null){
            JOptionPane.showMessageDialog(this, "Selecciona un torneo primero.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this),
                "Añadir Participante", Dialog.ModalityType.APPLICATION_MODAL);

        JPanel cont = new JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cont.add(new JLabel("Nombre del participante:"));
        JTextField txtNombre = new JTextField(20);
        cont.add(txtNombre);

        cont.add(Box.createVerticalStrut(10));
        cont.add(new JLabel("ID del participante (numero entero):"));
        JTextField txtID = new JTextField(20);
        cont.add(txtID);

        cont.add(Box.createVerticalStrut(10));
        cont.add(new JLabel("Tipo de participante:"));
        JRadioButton rbPersona = new JRadioButton("Persona");
        JRadioButton rbEquipo = new JRadioButton("Equipo");
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(rbPersona);
        grupoTipo.add(rbEquipo);
        cont.add(rbPersona);
        cont.add(rbEquipo);

        cont.add(Box.createVerticalStrut(10));
        JButton btnAceptar = new JButton("Aceptar");
        cont.add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String id = txtID.getText().trim();
            boolean tipoSeleccionado = rbPersona.isSelected() || rbEquipo.isSelected();

            if (nombre.isEmpty() || id.isEmpty() || !tipoSeleccionado) {
                JOptionPane.showMessageDialog(dialog, "Completa todo.",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tipo = rbPersona.isSelected() ? "Persona" : "Equipo";
            int np = seleccionado.getNumParticipantes();
            if (tipoSeleccionado == rbPersona.isSelected()){
                seleccionado.addPersona(nombre,id);
            }
            else if (tipoSeleccionado == rbEquipo.isSelected()){
                seleccionado.addEquipo(nombre,id);
            }
            dialog.dispose();

        });

        dialog.setContentPane(cont);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void mostrarDialogoGanadores() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un torneo primero.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<Participante> participantes = seleccionado.getPActivos();

        if (participantes.size() % 2 != 0) {
            JOptionPane.showMessageDialog(this, "Número impar de participantes en esta ronda.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantMatches = participantes.size() / 2;

        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this),
                "Seleccionar Ganadores", Dialog.ModalityType.APPLICATION_MODAL);

        JPanel cont = new JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ButtonGroup[] grupos = new ButtonGroup[cantMatches];

        for (int i = 0; i < cantMatches * 2; i += 2) {
            Participante p1 = participantes.get(i);
            Participante p2 = participantes.get(i + 1);

            JPanel panelMatch = new JPanel();
            panelMatch.setLayout(new BoxLayout(panelMatch, BoxLayout.Y_AXIS));
            panelMatch.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            panelMatch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
            panelMatch.setPreferredSize(new Dimension(500, 80));

            JLabel titulo = new JLabel("Enfrentamiento: " + p1.getNombre() + " vs " + p2.getNombre());
            titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
            titulo.setFont(titulo.getFont().deriveFont(Font.BOLD));

            JRadioButton rb1 = new JRadioButton(p1.getNombre());
            JRadioButton rb2 = new JRadioButton(p2.getNombre());

            rb1.setActionCommand(p1.getNombre());
            rb2.setActionCommand(p2.getNombre());

            ButtonGroup grupo = new ButtonGroup();
            grupo.add(rb1);
            grupo.add(rb2);

            grupos[i / 2] = grupo;

            panelMatch.add(titulo);
            panelMatch.add(rb1);
            panelMatch.add(rb2);

            cont.add(panelMatch);
            cont.add(Box.createVerticalStrut(5));
        }

        JButton btnAceptar = new JButton("Aceptar");
        cont.add(Box.createVerticalStrut(10));
        cont.add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            // Validar selección completa
            for (ButtonGroup g : grupos) {
                if (g.getSelection() == null) {
                    JOptionPane.showMessageDialog(dialog, "Debes seleccionar un ganador para cada enfrentamiento.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            ArrayList<Participante> ganadores = new ArrayList<>();

            for (int i = 0; i < cantMatches; i++) {
                Participante p1 = participantes.get(i * 2);
                Participante p2 = participantes.get(i * 2 + 1);

                ButtonGroup g = grupos[i];
                String nombreSeleccionado = g.getSelection().getActionCommand();

                Participante ganador = nombreSeleccionado.equals(p1.getNombre()) ? p1 : p2;
                ganadores.add(ganador);
            }
            seleccionado.pasarRondas(ganadores);
            if (seleccionado.getPActivos().size() == 1) {
                seleccionado.setStatus();

                JOptionPane.showMessageDialog(dialog,
                        "¡El torneo ha finalizado! El ganador es: " +
                                seleccionado.getPActivos().get(0).getNombre(),
                        "Torneo finalizado", JOptionPane.INFORMATION_MESSAGE);

                enCurso.remove(seleccionado);
                finalizados.add(seleccionado);

                modeloEnCurso.removeElement(seleccionado);
                modeloFinalizados.addElement(seleccionado);

                actualizarListas();

                seleccionado = null;
            }

            dialog.dispose();
        });

        JScrollPane scrollPane = new JScrollPane(cont);
        scrollPane.setPreferredSize(new Dimension(550, 400));

        dialog.setContentPane(scrollPane);
        dialog.pack();
        dialog.setSize(600, dialog.getHeight());
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void actualizarListas() {
        modeloEnCurso.clear();
        modeloFinalizados.clear();

        for (Torneo t : enCurso) {
            modeloEnCurso.addElement(t);
        }
        listaEnCurso.clearSelection();
        listaEnCurso.revalidate();
        listaEnCurso.repaint();

        for (Torneo t : finalizados) {
            modeloFinalizados.addElement(t);
        }
        listaFinalizados.clearSelection();
        listaFinalizados.revalidate();
        listaFinalizados.repaint();
    }

}

