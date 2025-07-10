package Logica.Participantes;

public class Persona extends Participante{

    public Persona(String n, int x){
        super(n,x);
    }

    @Override
    public String toString(){
        return super.nombre;
    }
}
