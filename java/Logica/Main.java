package Logica;
import GUI.*;
import Logica.TiposTorneo.Torneo;

public class Main {
    public static void main(String[] args) {

        CrearTorneo crearTorneo = new CrearTorneo(1,6);
        Torneo t = crearTorneo.getTorneo();

        t.addPersona("Pepe",1);
        t.addPersona("Pepa",2);
        t.addPersona("Pepon",3);
        t.addPersona("Pepito",4);
        t.addPersona("Bob", 5);
        t.addPersona("Alice",6);

        System.out.println(t.generarTorneo());

        VentanaPrincipal p = new VentanaPrincipal();
    }
}
