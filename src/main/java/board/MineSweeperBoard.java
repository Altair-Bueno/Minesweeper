package board;

import java.util.HashMap;
import java.util.Map;

public class MineSweeperBoard {
    /*
    Keeps track of the game board and interrupts the code execution with a GameOver exception
    if the game is over
    -1,0,1,2,3...8
     */

    public static final int MINE = -1;
    public static final int EMPTY = 0;


    private final int[][] board;
    private final boolean[][] isVisible;
    private final boolean[][] isFlagged;
    private final int numMines;
    private int flagNumber;
    private int leftOver;
    private boolean gameHasStarted;

    //Track visibility changes
    private Map<Coordinate, Integer> changes;

    //Constructors
    public MineSweeperBoard(int x, int y, int numMines) {
        board = new int[x][y];
        isVisible = new boolean[x][y];
        isFlagged = new boolean[x][y];

        this.numMines = numMines;
        flagNumber = numMines;
        leftOver = x * y;

        gameHasStarted = false;
        changes = null;
    }

    public MineSweeperBoard(int size, int numMines) {
        this(size, size, numMines);
    }

    private static <E> E getRandomWeighted(Map<E, ? extends Number> balancedObjects) {
        double totalWeight = balancedObjects.values().stream().mapToInt(Number::intValue).sum(); // Java 8

        double value = Math.random() * totalWeight, weight = 0;

        for (Map.Entry<E, ? extends Number> e : balancedObjects.entrySet()) {
            weight += e.getValue().doubleValue();
            if (value < weight) return e.getKey();
        }
        throw new RuntimeException("Error on getRandomWeighted");
    }

    private void plantWeightedMines(int x, int y) {
        Map<Coordinate, Double> weightMap = new HashMap<>();

        for (int i = 0; i < getNumRow(); i++) {
            for (int u = 0; u < getNumColum(); u++) {
                int temp = Math.abs(x - i) + Math.abs(y - u);
                double tempWeight = (temp <= 2) ? 0 : 1;
                weightMap.put(new Coordinate(i, u), tempWeight);
            }
        }

        int plantedMines = 0;

        while (plantedMines < numMines) {
            Coordinate minPos = getRandomWeighted(weightMap);

            int fil = minPos.getRow();
            int col = minPos.getColumn();

            if (board[fil][col] != MINE) {
                weightMap.remove(minPos);
                board[fil][col] = MINE;
                plantedMines++;

                for (int i = fil - 1; i <= fil + 1; i++) {
                    for (int u = col - 1; u <= col + 1; u++) {

                        try {
                            if (board[i][u] != MINE) board[i][u]++;
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }

                    }
                } //For
            }
        }
        gameHasStarted = true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int[] a : board) {
            for (int i : a) {
                stringBuilder.append(i).append("\t");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    //Game Dig
    public void dig(Coordinate coordinate) {
        dig(coordinate.getRow(), coordinate.getColumn());
    }

    public void dig(int x, int y) {
        if (!gameHasStarted) plantWeightedMines(x, y);
        changes = new HashMap<>();
        showZone(x, y);
        if (board[x][y] == MINE)
            throw new GameOver(GameOver.MINEFOUND);
    }

    public void checkWin() {
        if (leftOver == numMines)
            throw new GameOver(GameOver.GAMEWON);
    }

    private void showZone(int x, int y) {
        if (!isFlagged[x][y] && !isVisible[x][y]) {
            leftOver--;
            isVisible[x][y] = true;
            changes.put(new Coordinate(x, y), board[x][y]);

            if (board[x][y] == EMPTY) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int u = y - 1; u <= y + 1; u++) {
                        try {
                            showZone(i, u);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }
        }
    }

    public void digNearby(Coordinate coordinate) {
        digNearby(coordinate.getRow(), coordinate.getColumn());
    }

    public void digNearby(int x, int y) {
        int numNearbyFlags = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int u = y - 1; u <= y + 1; u++) {
                try {
                    if (isFlagged[i][u]) numNearbyFlags++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        if (numNearbyFlags == board[x][y]) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int u = y - 1; u <= y + 1; u++) {
                    try {
                        showZone(i, u);
                        if (board[i][u] == MINE && !isFlagged[i][u])
                            throw new GameOver(GameOver.MINEFOUND);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
    }

    //getter
    public Map<Coordinate, Integer> getChanges() {
        return changes;
    }

    public int[][] getBoard() {
        return board;
    }

    public void addFlag(Coordinate coordinate) {
        addFlag(coordinate.getRow(), coordinate.getColumn());
    }

    public void addFlag(int x, int y) {
        isFlagged[x][y] = true;
        flagNumber--;
    }

    public void removeFlag(Coordinate coordinate) {
        removeFlag(coordinate.getRow(), coordinate.getColumn());
    }

    public void removeFlag(int x, int y) {
        isFlagged[x][y] = false;
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

    public boolean isDigged(int x, int y) {
        return isVisible[x][y];
    }

    public boolean isDigged(Coordinate coordinate) {
        return isDigged(coordinate.getRow(), coordinate.getColumn());
    }

}
