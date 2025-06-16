package Logica.TiposTorneo;

public abstract class Torneo {
    private int participantes;
    public Torneo(int x){
        this.participantes = x;
    }

    public int getParticipantes(){
        return participantes;
    }
}
