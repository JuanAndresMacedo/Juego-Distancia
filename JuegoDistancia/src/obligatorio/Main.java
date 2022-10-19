//Juan Andres Macedo - 290961 - M2B
//Lautaro Elosegui - 287788 - M2B
package Obligatorio;

import java.util.*;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {
        boolean finalizar = false;
        while (!finalizar) {
            mostrarMenu();
            String seleccionado = input.next();
            ejecutarMenu(seleccionado.toUpperCase());
            if (seleccionado.toUpperCase().equals("E")) {
                finalizar = true;
                System.out.println();
                System.out.println("Finalizado");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("(a) Registrar jugador");
        System.out.println("(b) Establecer tablero");
        System.out.println("(c) Jugar partida");
        System.out.println("(d) Ranking");
        System.out.println("(e) Fin");
        System.out.println();
    }

    private static void ejecutarMenu(String seleccionado) {
        if (seleccionado.equals("A")) {
            registrarJugador();
        } else {
            if (seleccionado.equals("B")) {
                establecerTablero();
            } else {
                if (seleccionado.equals("C")) {
                    jugarPartida();
                } else {
                    if (seleccionado.equals("D")) {
                        ranking();
                    } else {
                        if (!seleccionado.equals("E")) {
                            System.out.println();
                            System.out.println(ANSI_RED + "Ingreso de datos incorrecto, vuelva a intentar" + ANSI_RESET);
                            System.out.println();
                        }
                    }
                }
            }
        }
    }

    private static void registrarJugador() {
        System.out.println();

        System.out.println("Ingrese nombre del Jugador");
        String nombre = input.next();
        System.out.println("Ingrese edad del Jugador");
        int edad = leerNumero();
        System.out.println("Ingrese Alias del Jugador");
        String alias = input.next();

        System.out.println();

        Jugador jugador = new Jugador(nombre, edad, alias);

        boolean repetido = compararJugadores(jugador);

        while (repetido) {
            System.out.println(ANSI_RED + "Jugador existente, vuelva a ingresar el alias" + ANSI_RESET);
            alias = input.next();
            jugador = new Jugador(nombre, edad, alias);
            repetido = compararJugadores(jugador);
            System.out.println();
        }

        sistema.agregarJugador(jugador);
        System.out.println(ANSI_GREEN + "Registrado correctamente" + ANSI_RESET);
        System.out.println();
        input.nextLine();
    }

    private static void establecerTablero() {
        System.out.println();

        System.out.println("1)Tablero Standard");
        System.out.println("2)Tablero Precargado 1");
        System.out.println("3)Tablero Precargado 2");
        System.out.println();
        int seleccionado = leerNumero();
        System.out.println();

        //Validar datos correctos
        if (seleccionado >= 1 && seleccionado <= 3) {
            Tablero tablero = new Tablero(seleccionado);
            sistema.setTablero(tablero);
            System.out.println(ANSI_GREEN + "Tablero seleccionado " + seleccionado + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Tablero inexistente" + ANSI_RESET);
            establecerTablero();
        }

        System.out.println();
    }

    private static void jugarPartida() {
        System.out.println();

        ArrayList<Jugador> lista = sistema.getListaJugadores();

        //Validar que la lista tenga al menos dos jugadores
        if (lista.size() > 1) {
            Partida partida = new Partida();
            sistema.setPartida(partida);
            sistema.getPartida().setTablero(sistema.getTablero());
            int contador = 0;
            System.out.println("Jugar partida");
            System.out.println();
            System.out.println("Seleccione Jugadores");

            //Mostrar lista de Jugadores
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("(" + contador + ")" + lista.get(i));
                contador++;
            }

            System.out.println();

            //Seleccion de jugadores
            System.out.println("Seleccione jugador Rojo");
            int seleccionado1 = leerNumero();
            System.out.println();
            sistema.getPartida().setJugador1(lista.get(seleccionado1));
            System.out.println(sistema.getPartida().getJugador1());
            System.out.println();
            System.out.println(ANSI_GREEN + "Jugador Rojo seleccionado" + ANSI_RESET);
            System.out.println();
            System.out.println("Seleccione jugador Azul");
            int seleccionado2 = leerNumero();

            //Validar que no se elija el mismo jugador
            while (seleccionado1 == seleccionado2) {
                System.out.println();
                System.out.println(ANSI_RED + "Se ingreso el mismo jugador, vuelva a elegir" + ANSI_RESET);
                seleccionado2 = leerNumero();
            }

            sistema.getPartida().setJugador2(lista.get(seleccionado2));
            System.out.println();
            System.out.println(sistema.getPartida().getJugador2());
            System.out.println();
            System.out.println(ANSI_GREEN + "Jugador Azul seleccionado" + ANSI_RESET);

            System.out.println();
            input.nextLine();

            //Comienza partida
            boolean fin = false;
            int cont = 0;
            String ficha;
            String mat[][] = sistema.getTablero().getMatriz();
            String[][] matClon = new String[6][6];
            sistema.getPartida().getJugador1().setPartidasJugadas(sistema.getPartida().getJugador1().getPartidasJugadas() + 1);
            sistema.getPartida().getJugador2().setPartidasJugadas(sistema.getPartida().getJugador2().getPartidasJugadas() + 1);

            while (!fin) {
                mostrarMat(mat);
                System.out.println();

                //Cont par = jugador rojo /// Cont impar = jugador azul /// captura=oponente
                if (cont % 2 == 0) {
                    System.out.println("Turno Rojo " + "(" + sistema.getPartida().getJugador1().getAlias()
                            + ")" + ", ingrese jugada inicial");
                    ficha = ANSI_RED + "R" + ANSI_RESET;
                } else {
                    System.out.println("Turno Azul " + "(" + sistema.getPartida().getJugador2().getAlias()
                            + ")" + ", ingrese jugada inicial");
                    ficha = ANSI_BLUE + "A" + ANSI_RESET;
                }

                clonarMat(mat, matClon);
                if (sistema.getPartida().hayMovimientosDisponibles(matClon, ficha)) {
                    String jugada = leerJugadaInicial(ficha, mat);
                    String jugadaFinal = "";

                    if (!jugada.equals("X")) {
                        clonarMat(mat, matClon);
                        matClon = sistema.getPartida().tableroConJugadas(jugada, matClon, ficha);
                        
                        if (sistema.getPartida().hayMovimientoEnFichaEspecifica(matClon)) {
                            mostrarMat(matClon);
                            System.out.println();
                            System.out.println("Ingrese destino");
                            jugadaFinal = leerJugadaFinal(ficha, matClon);
                            
                            if (sistema.getPartida().validarMovimiento(jugadaFinal, matClon)) {
                                System.out.println();
                                if (!jugadaFinal.equals("X")) {
                                    sistema.getPartida().tableroLuegoDeMovimiento(jugada, jugadaFinal, mat, matClon, ficha);
                                    cont++;
                                } else {
                                    System.out.println("Confirma salir de la partida? Reingrese X");
                                    String confirmar = input.nextLine().toUpperCase();
                                    if (confirmar.equals("X")) {
                                        jugada = "X";
                                        jugadaFinal = "X";
                                    } else {
                                        jugada = "";
                                        jugadaFinal = "";
                                    }
                                }
                            }
                        }else{
                            System.out.println();
                            System.out.println("No existe jugada posible con esa ficha, reingrese");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Confirma salir de la partida? Reingrese X");
                        String confirmar = input.nextLine().toUpperCase();
                        if (confirmar.equals("X")) {
                            jugada = "X";
                            jugadaFinal = "X";
                        } else {
                            jugada = "";
                            jugadaFinal = "";
                        }
                    }

                    //Finalizar partida
                    if (!sistema.getPartida().continuarPartida(mat, ficha) || (jugada.equals("X")) || (jugadaFinal.equals("X"))) {
                        mostrarMat(mat);
                        fin = true;
                        System.out.println();
                        System.out.println("Partida finalizada");

                        //reinicio el tablero para la proxima partida
                        Tablero tableroNuevo = new Tablero();
                        sistema.setTablero(tableroNuevo);

                        if (!sistema.getPartida().continuarPartida(mat, ficha)) {
                            if (ficha.equals(ANSI_RED + "R" + ANSI_RESET)) {
                                System.out.println();
                                System.out.println("El Ganador es : " + sistema.getPartida().getJugador1());
                                System.out.println();
                                sistema.getPartida().getJugador1().setPartidasGanadas(sistema.getPartida().getJugador1().getPartidasGanadas() + 1);
                            } else {
                                System.out.println();
                                System.out.println("El Ganador es : " + sistema.getPartida().getJugador2());
                                System.out.println();
                                sistema.getPartida().getJugador2().setPartidasGanadas(sistema.getPartida().getJugador2().getPartidasGanadas() + 1);
                            }
                        } else {
                            if (cont % 2 == 0) {
                                System.out.println();
                                System.out.println("El Ganador es : " + sistema.getPartida().getJugador2());
                                System.out.println();
                                sistema.getPartida().getJugador2().setPartidasGanadas(sistema.getPartida().getJugador2().getPartidasGanadas() + 1);
                            } else {
                                System.out.println();
                                System.out.println("El Ganador es : " + sistema.getPartida().getJugador1());
                                System.out.println();
                                sistema.getPartida().getJugador1().setPartidasGanadas(sistema.getPartida().getJugador1().getPartidasGanadas() + 1);
                            }

                        }
                    }
                } else {
                    System.out.println();
                    System.out.println("No hay movimientos posibles, pasa turno");
                    System.out.println();
                    cont++;
                }
            }
        } else {
            System.out.println(ANSI_RED + "No se han ingresado suficientes Jugadores" + ANSI_RESET);
            System.out.println();
        }
    }

    private static void ranking() {
        System.out.println();

        System.out.println("Ranking");
        ArrayList<Jugador> lista = sistema.ordenarXGanadas();

        //Validar que hay jugadores
        if (lista.size() != 0) {
            for (Jugador j : lista) {
                System.out.println(j.getNombre()
                        + " / Alias: " + j.getAlias() + " / Ganadas: " + j.getPartidasGanadas()
                        + " / Jugadas: " + j.getPartidasJugadas());
            }
            System.out.println();
        } else {
            System.out.println();
            System.out.println("--Vacio--");
            System.out.println();
        }
    }

    private static void mostrarMat(String[][] mat) {
        String[][] listaT = sistema.getTablero().devolverMatCompleta(mat, sistema.getTablero().crearMarco());
        for (int i = 0; i < listaT.length; i++) {
            for (int j = 0; j < listaT[i].length; j++) {
                System.out.print(listaT[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean compararJugadores(Jugador jugador) {
        boolean repetido = false;
        //Se busca que no repitan los alias de los jugadores
        for (int i = 0; i < sistema.getListaJugadores().size() && !repetido; i++) {
            ArrayList<Jugador> lista = sistema.getListaJugadores();
            if (jugador.compararAlias(lista.get(i))) {
                repetido = true;
            }
        }
        return repetido;
    }

    public static String[][] clonarMat(String mat[][], String[][] matClon) {
        for (int i = 0; i < matClon.length; i++) {
            for (int j = 0; j < matClon[i].length; j++) {
                matClon[i][j] = mat[i][j];
            }
        }
        return matClon;
    }

    public static int leerNumero() {
        int dato = 0;
        boolean correcto = false;
        while (!correcto) {
            try {
                dato = input.nextInt();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println();
                System.err.println("Error, No se ha ingresado un numero, reintente");
                input.nextLine();
            }
        }
        return dato;
    }

    public static String leerJugadaInicial(String ficha, String mat[][]) {
        String jugada = input.nextLine().toUpperCase();
        ArrayList<String> listaJugadas = sistema.getPartida().listaJugadasPosibles();
        if (!jugada.equals("X")) {
            while ((!jugada.equals("X")
                    && (!listaJugadas.contains(jugada) || (!sistema.getPartida().validarJugada(ficha, jugada, mat))))) {
                if (!listaJugadas.contains(jugada)) {
                    System.out.println("Jugada invalida, coordenada inexistente");
                    jugada = input.nextLine().toUpperCase();
                } else {
                    if (mat[sistema.getPartida().traducirLetraJugada(jugada)][sistema.getPartida().traducirNumeroJugada(jugada)].equals(" ")) {
                        System.out.println("Jugada invalida, espacio vacio seleccionado");
                        jugada = input.nextLine().toUpperCase();
                    } else {
                        if (!sistema.getPartida().validarJugada(ficha, jugada, mat)) {
                            System.out.println("Jugada invalida, ficha del contrincante seleccionada");
                            jugada = input.nextLine().toUpperCase();
                        }
                    }
                }
            }
        }
        return jugada;
    }

    public static String leerJugadaFinal(String ficha, String mat[][]) {
        String jugada = input.nextLine().toUpperCase();
        ArrayList<String> listaJugadas = sistema.getPartida().listaJugadasPosibles();
        if (!jugada.equals("X")) {
            if (!listaJugadas.contains(jugada)) {
                System.out.println("Jugada invalida, coordenada inexistente");
                System.out.println();
            } else {
                if (!sistema.getPartida().validarMovimiento(jugada, mat)) {
                    if (mat[sistema.getPartida().traducirLetraJugada(jugada)][sistema.getPartida().traducirNumeroJugada(jugada)].equals(ANSI_GREEN + "E" + ANSI_RESET)) {
                        System.out.println("Jugada invalida, posicion elegida seleccionada nuevamente");
                        System.out.println();
                    } else {
                        System.out.println("Jugada invalida, no se eligio un movimiento con captura o sin captura ");
                        System.out.println();
                    }
                }
            }
        }
        return jugada;
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
}
