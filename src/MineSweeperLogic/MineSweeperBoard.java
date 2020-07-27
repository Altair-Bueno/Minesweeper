package MineSweeperLogic;

import java.util.Arrays;
import java.util.Random;

public abstract class MineSweeperBoard {

    /*
    Contenido del cablero vacío, minas o número de minas cercanas
    -1,0,1,2,3...
     */
    public static final int MINA=-1;
    public static final int EMPTY=0;


    private int [][] tablero;
    private boolean [][] visibilidadTablero;
    private final int numMinas;
    private int contadorBanderas;

    protected MineSweeperBoard(int x,int y,int numMinas){
        tablero=new int[x][y];
        visibilidadTablero=new boolean[x][y];
        this.numMinas=numMinas;
        contadorBanderas=0;
        plantMines();
        calculateNearbyMines();
    }
    protected MineSweeperBoard(int size,int numMinas){
        this(size,size,numMinas);
    }

    protected void plantMines(){
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
    protected void calculateNearbyMines(){
        for (int x=0; x<tablero.length;x++){
            for (int y=0; y<tablero[0].length;y++){
                if (tablero[x][y]==MINA){
                    try{
                        if(tablero[x-1][y-1]!=MINA) tablero[x-1][y-1]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if(tablero[x-1][y]!=MINA) tablero[x-1][y]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if(tablero[x-1][y+1]!=MINA) tablero[x-1][y+1]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if(tablero[x][y-1]!=MINA) tablero[x][y-1]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if(tablero[x][y+1]!=MINA) tablero[x][y+1]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if (tablero[x+1][y-1]!=MINA) tablero[x+1][y-1]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if(tablero[x+1][y]!=MINA)tablero[x+1][y]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    try{
                        if (tablero[x+1][y+1]!=MINA)tablero[x+1][y+1]++;
                    } catch (ArrayIndexOutOfBoundsException ignored){}
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

    public void dig(Coordenada coordenada){
        dig(coordenada.getColumna(),coordenada.getFila());
    }
    public void dig(int x,int y){
        switch (tablero[x][y]){
            case MINA:
                throw new GameOver("Explosión de mina",GameOver.MINEFOUND);
            case EMPTY:
                //TODO stuff
                break;
            default:
                //TODO stuff
        }
    }
}
