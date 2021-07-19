package manager;

import gui.GameGUI.GameListener;
import gui.GameGUI.GameWindow;
import gui.GameGUI.IGameWindow;
import gui.SetSize.ISetSizeWindow;
import gui.SetSize.SelectSizeWindow;
import gui.SetSize.SetSizeListener;
import board.MineSweeperBoard;

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
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(xSize, ySize, numMines);
        GameListener buttonControlador = new GameListener(gameWindow, mineSweeperBoard);
        gameWindow.setListener(buttonControlador);
    }
}