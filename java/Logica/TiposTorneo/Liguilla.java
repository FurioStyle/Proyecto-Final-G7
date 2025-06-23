package Logica.TiposTorneo;

import Logica.Participantes.Equipo;
import Logica.Participantes.Participante;
import Logica.Participantes.Persona;

import java.util.ArrayList;

/**
 * Genera un torneo ordenado por puntos, quien tenga mas puntos gana.
 */
public class Liguilla extends Torneo{
    protected ArrayList<Participante> participantes;

    public Liguilla(int x){
        super(x);
        this.participantes = new ArrayList<>();
    }

    @Override
    public void addPersona(String nombre, int id){
        Participante p = new Persona(nombre,id);
        this.participantes.add(p);
    }
    @Override
    public void addEquipo(String nombre, int id){
        Equipo p = new Equipo(nombre,id);
        this.participantes.add(p);
    }

    @Override
    public String generarTorneo() {
        return "";
    }
}
