package Logica.Participantes;

public abstract class Participante {
    protected int ID;
    protected String nombre;
    public Participante(String nombre, int id){
        this.ID = id;
        this.nombre = nombre;
    }

    public int getID() {
        return ID;
    }

}


