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
    private ArrayList<Participante> participantes;

    public DobleEliminacion(int x, String n){
        super(n);
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
}
