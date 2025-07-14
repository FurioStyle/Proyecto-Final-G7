package Logica.TiposTorneo;

import Logica.Participantes.Participante;

import java.util.ArrayList;

public abstract class Torneo {
    protected String nombre;
    protected String status;

    public Torneo(String n){
        this.status = "En Curso";
        this.nombre = n;
    }

    public abstract void addPersona(String nombre, String id);

    public abstract void addEquipo(String nombre, String id);

    public String getNombre(){
        return nombre;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(){
        status = "Finalizado";
    }

    public abstract int getNumParticipantes();
    @Override
    public String toString() {
        return nombre + " " + "\"" + status + "\"";
    }

    public abstract ArrayList<Participante> getParticipantes();

    public abstract ArrayList<Participante> getPActivos();

    public abstract void pasarRondas(ArrayList<Participante> ganadores);

    public abstract int getRonda();

    public abstract void setRonda(int x);
}
