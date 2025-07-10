package Logica.TiposTorneo;

public abstract class Torneo {
    protected String nombre;
    protected String status;

    public Torneo(String n){
        this.status = "En Curso";
        this.nombre = n;
    }

    public abstract void addPersona(String nombre, int id);

    public abstract void addEquipo(String nombre, int id);

    public String getNombre(){
        return nombre;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return nombre + " " + "\"" + status + "\"";
    }
}
