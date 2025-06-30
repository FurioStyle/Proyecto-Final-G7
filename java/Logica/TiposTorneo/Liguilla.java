package Logica.TiposTorneo;

import Logica.Participantes.Equipo;
import Logica.Participantes.Participante;
import Logica.Participantes.Persona;

import java.util.ArrayList;

/**
 * Genera un torneo ordenado por puntos, quien tenga más puntos gana.
 */
public class Liguilla extends Torneo{
    private ArrayList<Participante> participantes;
    private String nombre;
    private String status;

    public Liguilla(int x, String n){
        super(x);
        this.nombre = n;
        this.participantes = new ArrayList<>();
        this.status = "En curso";
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
