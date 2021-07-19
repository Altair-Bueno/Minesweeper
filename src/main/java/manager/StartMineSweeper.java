package manager;

import gui.board.GameListener;
import gui.board.GameWindow;
import gui.board.IGameWindow;
import gui.start.ISetSizeWindow;
import gui.start.SelectSizeWindow;
import gui.start.SetSizeListener;
import board.Board;

import java.util.concurrent.Semaphore;

public class StartMineSweeper implements Runnable {
    @Override
    public void run() {
        Semaphore semaphore = new Semaphore(0);
        ISetSizeWindow setSizeWindow = new SelectSizeWindow();
        SetSizeListener setSizeListener = new SetSizeListener(setSizeWindow, semaphore);
        setSizeWindow.setListener(setSizeListener);

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int xSize = setSizeListener.getxSize();
        int ySize = setSizeListener.getySize();
        int numMines = setSizeListener.getMines();

        System.gc();
        IGameWindow gameWindow = new GameWindow(xSize, ySize);
        Board board = new Board(xSize, ySize, numMines);
        GameListener buttonControlador = new GameListener(gameWindow, board);
        gameWindow.setListener(buttonControlador);
    }
}
