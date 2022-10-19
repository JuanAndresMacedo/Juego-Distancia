//Juan Andres Macedo - 290961 - M2B
//Lautaro Elosegui - 287788 - M2B
package Obligatorio;

import java.util.ArrayList;

public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;

    //Constructor
    public Partida() {
        this.jugador1 = null;
        this.jugador2 = null;
        this.tablero = new Tablero();
    }

    //Constructor
    public Partida(Tablero unTablero) {
        this.jugador1 = null;
        this.jugador2 = null;
        this.tablero = unTablero;
    }

    //Metodos
    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public static int traducirLetraJugada(String jugada) {
        int i = 0;
        char letra = jugada.charAt(0);

        if (letra == 'A') {
            i = 0;
        } else {
            if (letra == 'B') {
                i = 1;
            } else {
                if (letra == 'C') {
                    i = 2;
                } else {
                    if (letra == 'D') {
                        i = 3;
                    } else {
                        if (letra == 'E') {
                            i = 4;
                        } else {
                            if (letra == 'F') {
                                i = 5;
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public static int traducirNumeroJugada(String jugada) {
        return Character.getNumericValue(jugada.charAt(1)) - 1;
    }

    public String[][] tableroConJugadas(String jugada, String[][] mat, String ficha) {

        String[][] matrizModificable = mat;
        int x = traducirLetraJugada(jugada);
        int y = traducirNumeroJugada(jugada);
        int[][] matrizDistancias = this.getTablero().crearTableroDistancias();

        String fichaBuscar;

        if (ficha.equals(ANSI_RED + "R" + ANSI_RESET)) {
            fichaBuscar = ANSI_BLUE + "A" + ANSI_RESET;
        } else {
            fichaBuscar = ANSI_RED + "R" + ANSI_RESET;
        }

        //Posicion elegida
        matrizModificable[traducirLetraJugada(jugada)][traducirNumeroJugada(jugada)] = ANSI_GREEN + "E" + ANSI_RESET;

        //Simbolos sin captura
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (lugarValido(matrizModificable, i, j)) {
                    if (matrizModificable[i][j].equals(" ") && (matrizDistancias[x][y] < matrizDistancias[i][j])) {
                        matrizModificable[i][j] = ANSI_GREEN + "*" + ANSI_RESET;
                    }
                }
            }
        }

        //Simbolos con captura
        boolean corte = false;

        //Recorre filas hacia abajo
        for (int i = x + 1; i < matrizModificable.length && !corte; i++) {
            if ((matrizDistancias[i][y] <= matrizDistancias[x][y]) && (matrizModificable[i][y].equals(fichaBuscar))) {
                matrizModificable[i][y] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[i][y].equals(" ")) {
                    corte = true;
                }
            }
        }

        corte = false;
        //Recorre filas hacia arriba
        for (int i = x - 1; i >= 0 && !corte; i--) {
            if ((matrizDistancias[i][y] <= matrizDistancias[x][y]) && (matrizModificable[i][y].equals(fichaBuscar))) {
                matrizModificable[i][y] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[i][y].equals(" ")) {
                    corte = true;
                }
            }
        }

        corte = false;
        //Recorre columnas hacia derecha
        for (int j = y + 1; j < matrizModificable[0].length && !corte; j++) {
            if ((matrizDistancias[x][j] <= matrizDistancias[x][y]) && (matrizModificable[x][j].equals(fichaBuscar))) {
                matrizModificable[x][j] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[x][j].equals(" ")) {
                    corte = true;
                }
            }
        }

        corte = false;
        //Recorre columnas hacia izquierda
        for (int j = y - 1; j >= 0 && !corte; j--) {
            if ((matrizDistancias[x][j] <= matrizDistancias[x][y]) && (matrizModificable[x][j].equals(fichaBuscar))) {
                matrizModificable[x][j] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[x][j].equals(" ")) {
                    corte = true;
                }
            }
        }

        corte = false;
        //Recorre diagonalmente de izquierda a derecha hacia arriba
        int i = x - 1;
        int j = y + 1;
        while ((i >= 0) && (j < matrizModificable[i].length) && !corte) {
            if ((matrizDistancias[i][j] <= matrizDistancias[x][y]) && (matrizModificable[i][j].equals(fichaBuscar))) {
                matrizModificable[i][j] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[i][j].equals(" ")) {
                    corte = true;
                }
            }
            i--;
            j++;
        }

        corte = false;
        //Recorre diagonalmente de derecha a izquierda hacia arriba
        i = x - 1;
        j = y - 1;
        while ((i >= 0) && (j >= 0) && !corte) {
            if ((matrizDistancias[i][j] <= matrizDistancias[x][y]) && (matrizModificable[i][j].equals(fichaBuscar))) {
                matrizModificable[i][j] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[i][j].equals(" ")) {
                    corte = true;
                }
            }
            i--;
            j--;
        }

        corte = false;
        //Recorre diagonalmente de izquierda a derecha hacia abajo
        i = x + 1;
        j = y + 1;
        while ((i < matrizModificable.length) && (j < matrizModificable[i].length) && !corte) {
            if ((matrizDistancias[i][j] <= matrizDistancias[x][y]) && (matrizModificable[i][j].equals(fichaBuscar))) {
                matrizModificable[i][j] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[i][j].equals(" ")) {
                    corte = true;
                }
            }
            i++;
            j++;
        }

        corte = false;
        //Recorre diagonalmente de derecha a izquierda hacia abajo
        i = x + 1;
        j = y - 1;
        while ((i < matrizModificable.length) && (j >= 0) && !corte) {
            if ((matrizDistancias[i][j] <= matrizDistancias[x][y]) && (matrizModificable[i][j].equals(fichaBuscar))) {
                matrizModificable[i][j] = ANSI_GREEN + "#" + ANSI_RESET;
                corte = true;
            } else {
                if (!matrizModificable[i][j].equals(" ")) {
                    corte = true;
                }
            }
            i++;
            j--;
        }

        return matrizModificable;
    }

    public String[][] tableroLuegoDeMovimiento(String jugadaInicial, String jugadaFinal,
            String[][] mat, String[][] matClon, String ficha) {
        int x1 = traducirLetraJugada(jugadaInicial);
        int y1 = traducirNumeroJugada(jugadaInicial);
        int x2 = traducirLetraJugada(jugadaFinal);
        int y2 = traducirNumeroJugada(jugadaFinal);

        if (matClon[x2][y2].equals(ANSI_GREEN + "*" + ANSI_RESET) || matClon[x2][y2].equals(ANSI_GREEN + "#" + ANSI_RESET)) {
            mat[x2][y2] = ficha;
            mat[x1][y1] = " ";
        }

        return mat;
    }

    public boolean hayMovimientosDisponibles(String mat[][], String ficha) {
        boolean hay = false;
        for (int i = 0; i < mat.length && !hay; i++) {
            for (int j = 0; j < mat[i].length && !hay; j++) {
                if (mat[i][j].equals(ficha)) {
                    String traducirPosicionAJugada = (char) (65 + i) + "" + (j + 1);
                    String matriz[][] = this.tableroConJugadas(traducirPosicionAJugada, mat, ficha);
                    for (int x = 0; x < matriz.length && !hay; x++) {
                        for (int y = 0; y < matriz[x].length && !hay; y++) {
                            if (mat[x][y].equals(ANSI_GREEN + "*" + ANSI_RESET) || mat[x][y].equals(ANSI_GREEN + "#" + ANSI_RESET)) {
                                hay = true;
                            }
                        }
                    }
                }
            }
        }
        return hay;
    }

    public boolean hayMovimientoEnFichaEspecifica(String mat[][]) {
        boolean hay = false;
        for (int x = 0; x < mat.length && !hay; x++) {
            for (int y = 0; y < mat[x].length && !hay; y++) {
                if (mat[x][y].equals(ANSI_GREEN + "*" + ANSI_RESET) || mat[x][y].equals(ANSI_GREEN + "#" + ANSI_RESET)) {
                    hay = true;
                }
            }
        }
        return hay;
    }

    public boolean continuarPartida(String mat[][], String ficha) {
        String fichaBuscar;

        if (ficha.equals(ANSI_RED + "R" + ANSI_RESET)) {
            fichaBuscar = ANSI_BLUE + "A" + ANSI_RESET;
        } else {
            fichaBuscar = ANSI_RED + "R" + ANSI_RESET;
        }

        boolean continua = false;
        for (int i = 0; i < mat.length && !continua; i++) {
            for (int j = 0; j < mat[i].length && !continua; j++) {
                if (mat[i][j].equals(fichaBuscar)) {
                    continua = true;
                }
            }
        }
        return continua;
    }

    public static boolean lugarValido(String mat[][], int fil, int col) {
        return (fil >= 0 && fil < mat.length) && (col >= 0 && col < mat[fil].length);
    }

    //Validar que sea elegida la ficha del jugador correspondiente
    public static boolean validarJugada(String ficha, String jugada, String mat[][]) {
        boolean valido = false;
        if (mat[traducirLetraJugada(jugada)][traducirNumeroJugada(jugada)].equals(ficha)) {
            valido = true;
        }
        return valido;
    }

    //Validar que sea elegida una posicion con captura o sin captura
    public static boolean validarMovimiento(String jugada, String mat[][]) {
        boolean valido = false;

        //Mientras la jugada no sea x, consulte, si lo es, que retorne true para que devuelva que la jugada sea x
        if (!jugada.equals("X")) {
            if (listaJugadasPosibles().contains(jugada)) {
                int i = traducirLetraJugada(jugada);
                int j = traducirNumeroJugada(jugada);
                if (mat[i][j].equals(ANSI_GREEN + "*" + ANSI_RESET) || mat[i][j].equals(ANSI_GREEN + "#" + ANSI_RESET)) {
                    valido = true;
                }
            }
        } else {
            valido = true;
        }
        return valido;
    }

    public static ArrayList<String> listaJugadasPosibles() {
        ArrayList<String> listaJugadas = new ArrayList<String>();
        for (int i = 65; i <= 70; i++) {
            for (int j = 1; j <= 6; j++) {
                listaJugadas.add(String.valueOf((char) i) + j);
            }
        }
        return listaJugadas;
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
}
