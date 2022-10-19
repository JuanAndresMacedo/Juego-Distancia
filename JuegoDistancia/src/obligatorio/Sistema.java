//Juan Andres Macedo - 290961 - M2B
//Lautaro Elosegui - 287788 - M2B
package Obligatorio;

import java.util.*;

public class Sistema {

    //Atributos
    private ArrayList<Jugador> listaJugadores;
    private Partida partida;
    private Tablero tablero;

    //Constructor
    public Sistema() {
        listaJugadores = new ArrayList();
        this.partida = new Partida();
        this.tablero = new Tablero();
    }

    //Metodos
    public ArrayList<Jugador> ordenarXGanadas() {
        Collections.sort(listaJugadores);
        return listaJugadores;
    }

    public void agregarJugador(Jugador unJugador) {
        this.listaJugadores.add(unJugador);
    }

    public ArrayList<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
