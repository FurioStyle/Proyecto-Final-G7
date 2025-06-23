package Logica;
import Logica.TiposTorneo.*;

public class CrearTorneo {
    private int tipoTorneo;
    private int  numParticipantes;
    private Torneo torneo;

    public CrearTorneo(int t, int p) {
        this.tipoTorneo = t;
        this.numParticipantes = p;

        switch (tipoTorneo){
            case (1):
                torneo = new Bracket(numParticipantes);
                System.out.println("Nuevo torneo creado, Bracket");
                break;
            case (2):
                torneo = new Liguilla(numParticipantes);
                System.out.println("Nuevo torneo creado, Liguilla");
                break;
            case (3):
                torneo = new DobleEliminacion(numParticipantes);
                System.out.println("Nuevo torneo creado, Liguilla");
                break;
        }
    }

    public Torneo getTorneo() {
        return torneo;
    }
}
