//Juan Andres Macedo - 290961 - M2B
//Lautaro Elosegui - 287788 - M2B

package Obligatorio;

public class Tablero {

    //Atributos
    private String[][] matriz;
    private int tipo;

    //Constuctor
    public Tablero() {
        this.matriz=crearTableroStandard();
    }

    //Constructor
    public Tablero(int unTipo) {
        this.tipo = unTipo;
        if (tipo == 1) {
            this.matriz = crearTableroStandard();
        } else {
            if (tipo == 2) {
                this.matriz = crearTableroPre1();
            } else {
                if (tipo == 3) {
                    this.matriz = crearTableroPre2();
                }
            }
        }
    }

    //Metodos
    public String[][] getMatriz() {
        return this.matriz;
    }
    
    public String[][] crearTableroStandard() {
        String[][] mat = new String[6][6];
        for (int i = 0; i < mat.length; i++) {
            String char1;
            String char2;
            if (i % 2 == 0) {
                char1 = ANSI_BLUE + "A" + ANSI_RESET;
                char2 = ANSI_RED + "R" + ANSI_RESET;
            } else {
                char1 = ANSI_RED + "R" + ANSI_RESET;
                char2 = ANSI_BLUE + "A" + ANSI_RESET;
            }
            for (int j = 0; j < mat[i].length; j++) {
                if (j % 2 == 0) {
                    mat[i][j] = char1;
                } else {
                    mat[i][j] = char2;
                }
            }
        }
        return mat;
    }
    
    public String[][] crearTableroPre1() {
        String[][] mat = new String[6][6];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j<mat[i].length;j++){
                if((i==0 && j==0)||(i==5 && j==3)){
                    mat[i][j]=ANSI_RED + "R" + ANSI_RESET;
                }else{
                    if((i==2 && j==2) || (i==3 && j==5) || (i==4 && j==1)){
                        mat[i][j]=ANSI_BLUE + "A" + ANSI_RESET;
                    }else{
                        mat[i][j]=" ";
                    }
                }
            }
        }
        return mat;
    }
    
    public String[][] crearTableroPre2() {
        String[][] mat = new String[6][6];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j<mat[i].length;j++){
                if(i==0 && j==0){
                    mat[i][j]=ANSI_RED + "R" + ANSI_RESET;
                }else{
                    if((i==5 && j==4) || (i==5 && j==5)){
                        mat[i][j]=ANSI_BLUE + "A" + ANSI_RESET;
                    }else{
                        mat[i][j]=" ";
                    }
                }
            }
        }
        return mat;
    }

    public String[][] crearMarco() {
        String[][] mat = new String[15][14];
        int caracterNum = 65;
        for (int i = 0; i < mat.length; i++) {
            int contador = 1;
            for (int j = 0; j < mat[i].length; j++) {

                //Filas de numeros
                if ((i == 0) || (i == 14)) {
                    if (j == 0) {
                        mat[i][j] = "  ";
                    } else {
                        if ((contador <= 6) && (j >= 3) && (j <= 8)) {
                            mat[i][j] = String.valueOf(contador) + " ";
                            contador++;
                        } else {
                            mat[i][j] = " ";
                        }
                    }
                } else {
                    mat[i][j] = "0";
                }
                
                //Columnas de letras
                if ((i == 2) || (i == 4) || (i == 6) || (i == 8) || (i == 10) || (i == 12)) {
                    if ((caracterNum <= 70) && (j == 13)) {
                        mat[i][0] = String.valueOf((char) caracterNum) + "  |";
                        mat[i][j] = "  " + String.valueOf((char) caracterNum);
                        caracterNum++;
                    }
                }
                
                //Filas de signos
                if ((i == 1) || (i == 3)
                        || (i == 5) || (i == 7)
                        || (i == 9) || (i == 11) || (i == 13)) {
                    if (j == 0) {
                        mat[i][j] = "  ";
                    } else {
                        if ((j >= 2) && (j <= 7)) {
                            mat[i][j] = "+-";
                        } else {
                            mat[i][j] = " ";
                        }
                        if (j == 7) {
                            mat[i][j] = "+-+";
                        }
                    }
                }
                if ((j == 2)
                        || (j == 4) || (j == 6)
                        || (j == 8) || (j == 10) || (j == 12)) {
                    if (((i == 2) || (i == 4))
                            || (i == 6) || (i == 8)
                            || (i == 10) || (i == 12)) {
                        mat[i][j] = "|";
                    }
                }
            }
        }
        return mat;
    }
    
    public int[][] crearTableroDistancias() {
        int[][] mat = new int[6][6];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if((i==0 || i==5) && (j==0 || j==5)){
                    mat[i][j]=6;
                }else{
                    if(((i==1 || i==4)&&(j==0 || j==5))||((i==0 || i==5)&&(j==1 || j==4))){
                        mat[i][j]=5;
                    }else{
                        if(((i==2 || i==3)&&(j==0 || j==5))||((i==0 || i==5)&&(j==2||j==3))){
                            mat[i][j]=4;
                        }else{
                            if((i==1 || i==4)&&(j==1 || j==4)){
                                mat[i][j]=3;
                            }else{
                                if(((i==2 || i==3)&&(j==1 || j==4))||(i==1 || i==4)&&(j==2 || j==3)){
                                    mat[i][j]=2;
                                }else{
                                    mat[i][j]=1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return mat;
    }
    
    public String[][] devolverMatCompleta(String[][] mat, String[][] marco) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < marco.length; i++) {
            y = 0;
            for (int j = 0; j < marco[i].length; j++) {
                if (marco[i][j] == "0") {
                    if (x < mat.length && y < mat[0].length) {
                        marco[i][j] = mat[x][y] + ANSI_RESET;
                        y++;
                    }
                }
            }
            if (y == mat[0].length) {
                x++;
            }
        }
        return marco;
    }

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
}
