//Juan Andres Macedo - 290961 - M2B
//Lautaro Elosegui - 287788 - M2B
package Obligatorio;

public class Jugador implements Comparable<Jugador> {

    //Atributos
    private String nombre;
    private int edad;
    private String alias;
    private int partidasJugadas;
    private int partidasGanadas;

    //Constructor
    public Jugador(String unNombre, int unaEdad, String unAlias) {
        this.nombre = unNombre;
        this.edad = unaEdad;
        this.alias = unAlias;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
    }

    //Metodos
    public String toString() {
        return "Jugador: " + this.getNombre() + " / Alias: " + this.getAlias();
    }

    @Override
    public boolean equals(Object o) {
        Jugador jug = ((Jugador) o);
        return this.getAlias() == jug.getAlias();
    }

    @Override
    public int compareTo(Jugador j) {
        return j.getPartidasGanadas() - this.getPartidasGanadas();
    }

    public boolean compararAlias(Jugador unJugador) {
        return this.getAlias().equals(unJugador.getAlias());
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int unaEdad) {
        this.edad = unaEdad;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String unAlias) {
        this.alias = unAlias;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }
}
