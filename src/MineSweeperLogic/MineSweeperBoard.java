package MineSweeperLogic;

import java.util.HashMap;
import java.util.Map;

public class MineSweeperBoard {
    /*
    Contenido del cablero vacío, minas o número de minas cercanas
    -1,0,1,2,3...8
     */

    public static final int MINA = -1;
    public static final int EMPTY = 0;


    private final int[][] tablero;
    private final boolean[][] isVisible;
    private final boolean[][] isFlagged;
    private final int numMinas;
    private int flagNumber;
    private int leftOver;
    private boolean gameHasStarted;

    //Constructors
    public MineSweeperBoard(int x, int y, int numMinas) {
        tablero = new int[x][y];
        isVisible = new boolean[x][y];
        isFlagged= new boolean[x][y];

        this.numMinas = numMinas;
        flagNumber = numMinas;
        leftOver = x * y;

        gameHasStarted=false;
        //plantMines();
    }

    public MineSweeperBoard(int size, int numMinas) {
        this(size, size, numMinas);
    }

    //Private method for constructors
    /*private void plantMines() {
        Random random = new Random();
        int minasPlantadas = 0;

        while (minasPlantadas < numMinas) {
            int x = random.nextInt(tablero.length);
            int y = random.nextInt(tablero[0].length);

            if (tablero[x][y] == EMPTY) {

                tablero[x][y] = MINA;
                minasPlantadas++;

                for (int i = x - 1; i <= x + 1; i++) {
                    for (int u = y - 1; u <= y + 1; u++) {

                        try {
                            if (tablero[i][u] != MINA) tablero[i][u]++;
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }

                    }
                } //For
            }//If
        } //While
    }

     */
    private void plantWeightedMines(int x, int y){
        Map<Coordenada,Double> weightMap = new HashMap<>();

        for (int i = 0; i<getNumRow();i++){
            for (int u=0;u<getNumColum();u++){
                int temp=Math.abs(x-i)+Math.abs(y-u);
                double tempWeight = (temp<=2) ? 0:1;
                weightMap.put(new Coordenada(i,u),tempWeight);
            }
        }

        int minasPlantadas=0;

        while (minasPlantadas<numMinas){
            Coordenada minPos=getRandomWeighted(weightMap);

            int fil= minPos.getFila();
            int col= minPos.getColumna();

            if (tablero[fil][col]!=MINA){
                weightMap.remove(minPos);
                tablero[fil][col]=MINA;
                minasPlantadas++;

                for (int i = fil - 1; i <= fil + 1; i++) {
                    for (int u = col - 1; u <= col + 1; u++) {

                        try {
                            if (tablero[i][u] != MINA) tablero[i][u]++;
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }

                    }
                } //For
            }
        }
        gameHasStarted=true;
    }
    private static <E> E getRandomWeighted(Map<E, ? extends Number> balancedObjects) {
        double totalWeight = balancedObjects.values().stream().mapToInt(Number::intValue).sum(); // Java 8

        double value = Math.random()*totalWeight, weight = 0;

        for (Map.Entry<E, ? extends Number> e : balancedObjects.entrySet()) {
            weight += e.getValue().doubleValue();
            if (value < weight)return e.getKey();
        }
        throw new RuntimeException("Error on getRandomWeighted");
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int[] a : tablero) {
            for (int i : a) {
                stringBuilder.append(i).append("\t");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    //Game Dig
    public void dig(Coordenada coordenada) {
        dig(coordenada.getFila(), coordenada.getColumna());
    }

    public void dig(int x, int y) {
        if (!gameHasStarted) plantWeightedMines(x,y);
        showZone(x, y);
        if (tablero[x][y] == MINA)
            throw new GameOver(GameOver.MINEFOUND);
    }

    public void checkWin() {
        if (leftOver == numMinas)
            throw new GameOver(GameOver.GAMEWON);
    }

    //Method for cleaning 0 zones
    /*
    private void showZone(int x, int y) {
        if (tablero[x][y] == EMPTY) {
            isVisible[x][y] = true;
            leftOver--;
            for (int i = x - 1; i <= x + 1; i++) {
                for (int u = y - 1; u <= y + 1; u++) {

                    try {
                        if (!isVisible[i][u]) showZone(i, u);
                    } catch (ArrayIndexOutOfBoundsException ignored) {}

                }
            }

        } else {
            isVisible[x][y] = true;
            leftOver--;
        }
    } */

    private void showZone(int x, int y) {
        if (!isVisible[x][y] ){
            if(tablero[x][y]!=MINA)leftOver--;
            isVisible[x][y]=true;
        }

        if (tablero[x][y]==EMPTY){
            for (int i = x - 1; i <= x + 1; i++) {
                for (int u = y - 1; u <= y + 1; u++) {
                    try {
                        if (!isVisible[i][u]) showZone(i, u);
                    } catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }

    public void digNearby(Coordenada coordenada){digNearby(coordenada.getFila(),coordenada.getColumna());}

    public void digNearby(int x,int y){
        int numNearbyFlags=0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int u = y - 1; u <= y + 1; u++) {
                try {
                    if (isFlagged[i][u]) numNearbyFlags++;
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        if (numNearbyFlags==tablero[x][y]){
            for (int i = x - 1; i <= x + 1; i++) {
                for (int u = y - 1; u <= y + 1; u++) {
                    try {
                        showZone(i, u);
                        if (tablero[i][u] == MINA && !isFlagged[i][u])
                            throw new GameOver(GameOver.MINEFOUND);
                    } catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }

    //getter
    public boolean[][] getVisibility() {
        return isVisible;
    }

    public int[][] getTablero() {
        return tablero;
    }
    public void addFlag(Coordenada coordenada){addFlag(coordenada.getFila(),coordenada.getColumna());}
    public void addFlag(int x,int y) {
        isFlagged[x][y]=true;
        flagNumber--;
    }

    public void removeFlag(Coordenada coordenada){removeFlag(coordenada.getFila(),coordenada.getColumna());}

    public void removeFlag(int x , int y) {
        isFlagged[x][y]=false;
        flagNumber++;
    }

    public int getFlagNumber() {
        return flagNumber;
    }

    public int getNumRow() {
        return isVisible.length;
    }

    public int getNumColum() {
        return isVisible[0].length;
    }

    public boolean isDigged(int x,int y){return isVisible[x][y];}

    public boolean isDigged(Coordenada coordenada){return isDigged(coordenada.getFila(),coordenada.getColumna());}

}
