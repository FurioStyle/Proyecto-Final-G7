package Logica.Participantes;

public class Equipo extends Participante{

    public Equipo(String n, int x){
        super(n,x);
    }

    @Override
    public String toString(){
        return super.nombre;
    }
}
