package Logica.TiposTorneo;

import Logica.Participantes.Equipo;
import Logica.Participantes.Participante;
import Logica.Participantes.Persona;

import java.util.ArrayList;

/**
 * Genera un torneo de Brackets 1vs1
 */


public class Bracket extends Torneo{
    public ArrayList<Participante> participantes;
    public ArrayList<Participante> pActivos;
    private int numParticipantes;

    public Bracket(int x, String n){
        super(n);
        this.numParticipantes = x;
        this.participantes = new ArrayList<>();
        this.pActivos = new ArrayList<>();

    }
    @Override
    public void addPersona(String nombre, String id){
        Participante p = new Persona(nombre,id);
        participantes.add(p);
        pActivos.add(p);
    }
    @Override
    public void addEquipo(String nombre, String id){
        Equipo p = new Equipo(nombre,id);
        participantes.add(p);
        pActivos.add(p);
    }

    @Override
    public int getNumParticipantes() {
        return numParticipantes;
    }

    public void pasarRondas(ArrayList<Participante> ganadores){
        pActivos = new ArrayList<>(ganadores);
    }

    @Override
    public ArrayList<Participante> getParticipantes(){
        return participantes;
    }

    @Override
    public ArrayList<Participante> getPActivos() {
        return pActivos;
    }
}
