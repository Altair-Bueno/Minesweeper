package MineSweeperLogic;

import MineSweeperGUI.GameGUI.GameControlador;
import MineSweeperGUI.GameGUI.GameWindow;
import MineSweeperGUI.GameGUI.IGameWindow;
import MineSweeperGUI.SetSize.ISetSizeWindow;
import MineSweeperGUI.SetSize.SelectSizeWindow;
import MineSweeperGUI.SetSize.SetSizeControlador;

import java.util.concurrent.Semaphore;

public class StartMineSweeper implements Runnable{
    @Override
    public void run() {
        Semaphore semaphore=new Semaphore(0);
        ISetSizeWindow setSizeWindow= new SelectSizeWindow();
        SetSizeControlador setSizeControlador=new SetSizeControlador(setSizeWindow,semaphore);
        setSizeWindow.setControlador(setSizeControlador);

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int xSize= setSizeControlador.getxSize();
        int ySize= setSizeControlador.getySize();
        int numMines= setSizeControlador.getMines();

        System.gc();
        IGameWindow gameWindow=new GameWindow(xSize,ySize);
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(xSize,ySize,numMines);
        GameControlador buttonControlador = new GameControlador(gameWindow,mineSweeperBoard);
        gameWindow.setControlador(buttonControlador);
    }
}
