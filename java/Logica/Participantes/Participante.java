package Logica.Participantes;

public abstract class Participante {
    protected String ID;
    protected String nombre;
    public Participante(String nombre, String id){
        this.ID = id;
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public String getID() {
        return ID;
    }

}


