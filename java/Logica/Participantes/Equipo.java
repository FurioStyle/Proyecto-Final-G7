package Logica.Participantes;

public class Equipo extends Participante{

    public Equipo(String n, String x){
        super(n,x);
    }

    @Override
    public String toString(){
        return super.nombre;
    }
}
