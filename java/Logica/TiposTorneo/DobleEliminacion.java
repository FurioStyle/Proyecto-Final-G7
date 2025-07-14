package Logica.TiposTorneo;

import Logica.Participantes.Equipo;
import Logica.Participantes.Participante;
import Logica.Participantes.Persona;

import java.util.ArrayList;

/**
 * Genera un torneo con la mecanica doble eliminacion, donde existen dos brackets, el upper bracket, y
 * el lower bracket, para que finalmente los ganadores de ambos brackets se enfrenten en la final.
 */
public class DobleEliminacion extends Torneo{
    public ArrayList<Participante> participantes;
    public ArrayList<Participante> pActivos;
    private ArrayList<Participante> upperBracket;
    private ArrayList<Participante> lowerBracket;
    private int numParticipantes;
    private int ronda = 0;

    public DobleEliminacion(int x, String n){
        super(n);
        this.numParticipantes = x;
        this.participantes = new ArrayList<>();
        this.pActivos = new ArrayList<>();
    }

    @Override
    public void addPersona(String nombre, String id){
        Participante p = new Persona(nombre,id);
        this.participantes.add(p);
        pActivos.add(p);
    }
    @Override
    public void addEquipo(String nombre, String id){
        Equipo p = new Equipo(nombre,id);
        participantes.add(p);
        pActivos.add(p);
    }

    @Override
    public int getNumParticipantes(){
        return numParticipantes;
    }

    @Override
    public ArrayList<Participante> getParticipantes(){
        return participantes;
    }

    @Override
    public ArrayList<Participante> getPActivos() {
        return pActivos;
    }

    @Override
    public void pasarRondas(ArrayList<Participante> ganadores){
        pActivos = new ArrayList<>(ganadores);
    }

    @Override
    public int getRonda() {
        return ronda;
    }

    @Override
    public void setRonda(int x) {
        this.ronda = x;
    }
}
