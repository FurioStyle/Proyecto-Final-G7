package Logica.TiposTorneo;

import Logica.Participantes.Equipo;
import Logica.Participantes.Participante;
import Logica.Participantes.Persona;

import java.util.ArrayList;

/**
 * Genera un torneo de Brackets 1vs1 al azar
 */


public class Bracket extends Torneo{
    private int numRondas;
    private String nombre;
    private String status;
    private ArrayList<Participante> participantes;


    public Bracket(int x, String n){
        super(x);
        this.nombre = n;
        this.numRondas = x/2;
        this.participantes = new ArrayList<>();
        this.status = "En curso";
    }
    @Override
    public void addPersona(String nombre, int id){
        Participante p = new Persona(nombre,id);
        participantes.add(p);
    }
    @Override
    public void addEquipo(String nombre, int id){
        Equipo p = new Equipo(nombre,id);
        participantes.add(p);
    }

    @Override
    public String generarTorneo() {
        StringBuilder enfrentamientos = new StringBuilder();

        for (int i = 0; i < participantes.size() - 1; i += 2) {
            enfrentamientos.append(participantes.get(i))
                    .append(" v/s ")
                    .append(participantes.get(i + 1))
                    .append("\n");
        }

        // Participante impar recibe bye
        if (participantes.size() % 2 != 0) {
            enfrentamientos.append(participantes.get(participantes.size() - 1))
                    .append(" pasa de ronda (bye)\n");
        }

        return enfrentamientos.toString();
    }

}
