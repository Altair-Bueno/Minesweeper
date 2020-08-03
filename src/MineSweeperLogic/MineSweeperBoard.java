package MineSweeperLogic;

import java.util.Random;

public class MineSweeperBoard {
    //TODO change board generation

    /*
    Contenido del cablero vacío, minas o número de minas cercanas
    -1,0,1,2,3...9
     */

    public static final int MINA = -1;
    public static final int EMPTY = 0;


    private final int[][] tablero;
    private final boolean[][] visibility;
    private final int numMinas;
    private int flagNumber;
    private int leftOver;

    //Constructors
    public MineSweeperBoard(int x, int y, int numMinas) {
        tablero = new int[x][y];
        visibility = new boolean[x][y];

        this.numMinas = numMinas;
        flagNumber = numMinas;
        leftOver = x * y;

        plantMines();
    }

    public MineSweeperBoard(int size, int numMinas) {
        this(size, size, numMinas);
    }

    //Private method for constructors
    private void plantMines() {
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
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int[] a : tablero) {
            for (int i : a) {
                stringBuilder.append(i + "\t");
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
        showZone(x, y);
        if (tablero[x][y] == MINA)
            throw new GameOver(GameOver.MINEFOUND);
    }

    public void checkWin() {
        if (leftOver == numMinas && flagNumber == 0)
            throw new GameOver(GameOver.GAMEWON);
    }

    //Method for cleaning 0 zones
    private void showZone(int x, int y) {
        if (tablero[x][y] == EMPTY) {
            visibility[x][y] = true;
            leftOver--;
            for (int i = x - 1; i <= x + 1; i++) {
                for (int u = y - 1; u <= y + 1; u++) {

                    try {
                        if (!visibility[i][u]) showZone(i, u);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                }
            }

        } else {
            visibility[x][y] = true;
            leftOver--;
        }
    }

    //getter
    public boolean[][] getVisibility() {
        return visibility;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void addFlag() {
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

    public int getNumColum() {
        return visibility[0].length;
    }

}
