package Logica.TiposTorneo;

import Logica.Participantes.Equipo;
import Logica.Participantes.Participante;
import Logica.Participantes.Persona;

import java.util.ArrayList;

/**
 * Genera un torneo de Brackets 1vs1 al azar
 */


public class Bracket extends Torneo{
    private ArrayList<Participante> participantes;
    private int numParticipantes;

    public Bracket(int x, String n){
        super(n);
        this.numParticipantes = x;
        this.participantes = new ArrayList<>();
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
}
