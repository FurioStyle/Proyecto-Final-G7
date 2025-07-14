package GUI;

import Logica.Participantes.Participante;
import Logica.TiposTorneo.Bracket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaBracket extends JFrame {

    public VentanaBracket(Bracket torneo) {
        setTitle("Visualización del Torneo Bracket");
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        ArrayList<Participante> participantes = torneo.getParticipantes();
        ArrayList<String[]> rondas = generarRondas(participantes);

        for (int i = 0; i < rondas.size(); i++) {
            String[] ronda = rondas.get(i);
            JPanel panelRonda = new JPanel();
            panelRonda.setBorder(BorderFactory.createTitledBorder("Ronda " + (i + 1)));
            panelRonda.setLayout(new GridLayout(ronda.length, 1));
            for (String enfrentamiento : ronda) {
                panelRonda.add(new JLabel(enfrentamiento));
            }
            panelPrincipal.add(panelRonda);
        }

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        add(scrollPane);
    }

    private ArrayList<String[]> generarRondas(ArrayList<Participante> participantes) {
        ArrayList<String[]> rondas = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        for (Participante p : participantes) {
            nombres.add(p.getNombre());
        }

        while (nombres.size() > 1) {
            ArrayList<String> siguienteRonda = new ArrayList<>();
            String[] rondaActual = new String[nombres.size() / 2];

            for (int i = 0; i < nombres.size(); i += 2) {
                String a = nombres.get(i);
                String b = (i + 1 < nombres.size()) ? nombres.get(i + 1) : "(Libre)";
                rondaActual[i / 2] = a + " vs " + b;
                siguienteRonda.add("Ganador de: " + a + " vs " + b);
            }

            rondas.add(rondaActual);
            nombres = siguienteRonda;
        }

        return rondas;
    }
}
