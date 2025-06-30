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
                torneo = new Bracket(numParticipantes, "Bracket Prueba");
                System.out.println("Nuevo torneo creado, Bracket");
                break;
            case (2):
                torneo = new Liguilla(numParticipantes, "Liguilla Prueba");
                System.out.println("Nuevo torneo creado, Liguilla");
                break;
            case (3):
                torneo = new DobleEliminacion(numParticipantes, "Doble Eliminacion Prueba");
                System.out.println("Nuevo torneo creado, DobleEliminacion");
                break;
        }
    }

    public Torneo getTorneo() {
        return torneo;
    }
}
