package Logica.TiposTorneo;

public abstract class Torneo {
    private int participantes;
    public Torneo(int x){
        this.participantes = x;
    }
    public abstract void addPersona(String nombre, int id);

    public abstract void addEquipo(String nombre, int id);

    public int getNumParticipantes(){
        return participantes;
    }
    public abstract String generarTorneo();
}
