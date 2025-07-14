package GUI;

import Logica.Participantes.Participante;
import Logica.Participantes.Persona;
import Logica.TiposTorneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
            if (seleccionado == null) {
                seleccionado = listaFinalizados.getSelectedValue();
            }

            if (seleccionado != null) {
                mostrarEnfrentamientos(seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un torneo en curso o finalizado", "Aviso", JOptionPane.WARNING_MESSAGE);
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
    private void mostrarEnfrentamientos(Torneo torneo) {
        JFrame ventana = new JFrame("Enfrentamientos del Torneo");
        ventana.setSize(800, 600);
        ventana.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));

        ArrayList<Participante> participantes = torneo.getParticipantes();
        int total = participantes.size();

        int inicio = 0;
        int rondaSize = calcularTamañoRondaInicial(total);
        int numRonda = 1;

        while (rondaSize >= 2 && inicio + rondaSize <= total) {
            ArrayList<Participante> ronda = new ArrayList<>(participantes.subList(inicio, inicio + rondaSize));

            JPanel panelRonda = new JPanel();
            panelRonda.setLayout(new BoxLayout(panelRonda, BoxLayout.Y_AXIS));
            panelRonda.setBorder(BorderFactory.createTitledBorder("Ronda " + numRonda));

            for (int i = 0; i < ronda.size(); i += 2) {
                Participante p1 = getParticipanteSegura(ronda, i);
                Participante p2 = (i + 1 < ronda.size()) ? ronda.get(i + 1) : null;

                JPanel enfrentamiento = crearEnfrentamiento(p1, p2);
                panelRonda.add(enfrentamiento);
                panelRonda.add(Box.createVerticalStrut(10));
            }

            panelPrincipal.add(panelRonda);
            panelPrincipal.add(Box.createHorizontalStrut(20));

            inicio += rondaSize;
            rondaSize /= 2;
            numRonda++;
        }

        if (inicio < total) {
            Participante ganador = participantes.get(total - 1);
            JPanel panelGanador = new JPanel();
            panelGanador.setLayout(new GridLayout(2, 1));
            panelGanador.setBorder(BorderFactory.createTitledBorder("Ganador"));
            panelGanador.setMaximumSize(new Dimension(200, 60));  // Tamaño similar a los enfrentamientos

            JLabel labelGanador = new JLabel(ganador.getNombre(), SwingConstants.CENTER);
            labelGanador.setFont(new Font("Arial", Font.PLAIN, 12));

            panelGanador.add(labelGanador);

            panelPrincipal.add(Box.createHorizontalStrut(20));
            panelPrincipal.add(panelGanador);
        }

        JScrollPane scroll = new JScrollPane(panelPrincipal);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);
        ventana.add(scroll);
        ventana.setVisible(true);
    }

    private int calcularTamañoRondaInicial(int total) {
        int size = 1;
        while (size * 2 <= total) {
            size *= 2;
        }
        return size;
    }


    private JPanel crearEnfrentamiento(Participante p1, Participante p2) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setMaximumSize(new Dimension(200, 50));

        String nombre1 = (p1 == null || p1.getNombre() == null || p1.getNombre().isBlank()) ? "-" : p1.getNombre();
        String nombre2 = (p2 == null || p2.getNombre() == null || p2.getNombre().isBlank()) ? "-" : p2.getNombre();

        JLabel label1 = new JLabel(nombre1, SwingConstants.CENTER);
        JLabel label2 = new JLabel(nombre2, SwingConstants.CENTER);

        panel.add(label1);
        panel.add(label2);

        return panel;
    }

    private Participante getParticipanteSegura(ArrayList<Participante> lista, int index) {
        if (index < lista.size()) {
            return lista.get(index);
        } else {
            return new Persona("-", "0");
        }
    }
}
