package Logica;
import Logica.TiposTorneo.*;

public class CrearTorneo {
    private int tipoTorneo;
    private int  numParticipantes;
    private Torneo torneo;
    private String nombre;

    public CrearTorneo(int t, int p, String n) {
        this.tipoTorneo = t;
        this.numParticipantes = p;
        this.nombre = n;

        switch (tipoTorneo){
            case (1):
                torneo = new Bracket(numParticipantes, nombre);
                System.out.println("Nuevo torneo creado, Bracket " + nombre);
                break;
            case (2):
                torneo = new DobleEliminacion(numParticipantes, nombre);
                System.out.println("Nuevo torneo creado, DobleEliminacion " + nombre);
                break;
        }
    }

    public Torneo getTorneo() {
        return torneo;
    }
}
