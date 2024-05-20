package maríaumaña.proyecto2;

public class Persona {

    //Se declaran los atributos de la clase persona
    String id;
    String nom;
    String pApellido;
    String sApellido;

    //Se crae el constructor de la clase persona
    public Persona(String id, String nom, String pApellido, String sApellido) {
        this.id = id;
        this.nom = nom;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
    }

    //Creación de los gettter's y setter's
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }
}
