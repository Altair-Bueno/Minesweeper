package MineSweeperLogic;

import java.util.Random;

public class MineSweeperBoard {

    public static final String ICON="res/icon.png";
    public static final String APPNAME="Mine Sweeper";

    /*
    Contenido del cablero vacío, minas o número de minas cercanas
    -1,0,1,2,3...
     */

    public static final int MINA=-1;
    public static final int EMPTY=0;


    private int [][] tablero;
    private boolean [][] visibility;
    private final int numMinas;
    private int flagNumber;
    private int leftOver;

    //Constructors
    public MineSweeperBoard(int x,int y,int numMinas){
        tablero=new int[x][y];
        visibility =new boolean[x][y];
        this.numMinas=numMinas;
        flagNumber =0;
        leftOver = x*y;
        plantMines();
        calculateNearbyMines();
    }
    public MineSweeperBoard(int size,int numMinas){
        this(size,size,numMinas);
    }

    //Private method for constructors
    private void plantMines(){
        Random random=new Random();
        int i=0;

        while (i<numMinas){
            int x=random.nextInt(tablero.length);
            int y=random.nextInt(tablero[0].length);
            if (tablero[x][y]!=MINA){
                tablero[x][y]=MINA;
                i++;
            }
        }
    }
    private void calculateNearbyMines(){
        for (int x=0; x<tablero.length;x++){
            for (int y=0; y<tablero[0].length;y++){

                if (tablero[x][y]==MINA){

                    for (int i=x-1; i<=x+1;i++) {
                        for (int u=y-1; u<=y+1;u++){

                            try {
                                if (tablero[i][u] != MINA) tablero[i][u]++;
                            }catch (ArrayIndexOutOfBoundsException ignored){}

                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();

        for (int [] a : tablero){
            for (int i : a){
                stringBuilder.append(i+"\t");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    //Game Dig
    public void dig(Coordenada coordenada){
        dig(coordenada.getFila(),coordenada.getColumna());
    }
    public void dig(int x,int y){
        switch (tablero[x][y]){
            case MINA:
                throw new GameOver("Explosión de mina",GameOver.MINEFOUND);
            default:
                showZone(x,y);
        }
        if (leftOver==numMinas && numMinas==flagNumber) throw new GameOver("Has ganado",GameOver.GAMEWON);
    }

    //Method for cleaning 0 zones
    private void showZone(int x,int y) {
        if (tablero[x][y] == EMPTY) {
            visibility[x][y]=true;
            leftOver--;
                for (int i=x-1; i<=x+1;i++) {
                    for (int u=y-1; u<=y+1;u++){

                        try {
                            if (!visibility[i][u]) showZone(i,u);
                        }catch (ArrayIndexOutOfBoundsException ignored){}

                    }
                }

        } else if (tablero[x][y] != MINA) {
            visibility[x][y]=true;
            leftOver--;
        }
    }

    //getter
    public boolean[][] getVisibility(){
        return visibility;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void addFlag(){
        flagNumber++;
    }
    public void removeFlag() {
        flagNumber--;
    }
    public int getFlagNumber() {
        return flagNumber;
    }
    public int getNumRow() {
        return visibility.length;
    }
    public int getNumColum(){
        return visibility[0].length;
    }

    public int getLeftOver() {
        return leftOver;
    }

    public int getNumMinas() {
        return numMinas;
    }
}
