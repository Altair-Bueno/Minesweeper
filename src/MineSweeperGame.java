import MineSweeperGUI.GameGUI.GameControlador;
import MineSweeperGUI.GameGUI.GameWindow;
import MineSweeperGUI.GameGUI.IGameWindow;
import MineSweeperGUI.SetSize.ISetSizeWindow;
import MineSweeperGUI.SetSize.SelectSizeWindow;
import MineSweeperGUI.SetSize.SetSizeControlador;
import MineSweeperLogic.MineSweeperBoard;

import java.util.concurrent.Semaphore;

public class MineSweeperGame {

    private MineSweeperBoard board;

    public MineSweeperGame() throws InterruptedException {
        //TODO
        startNewGame();
    }

    private void startNewGame() throws InterruptedException {
        Semaphore semaphore=new Semaphore(0);
        ISetSizeWindow setSizeWindow= new SelectSizeWindow();
        SetSizeControlador setSizeControlador=new SetSizeControlador(setSizeWindow,semaphore);
        setSizeWindow.setControlador(setSizeControlador);

        semaphore.acquire();

        int xSize= setSizeControlador.getxSize();
        int ySize= setSizeControlador.getySize();
        int numMines= setSizeControlador.getMines();

        IGameWindow gameWindow=new GameWindow(xSize,ySize);
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(xSize,ySize,numMines);
        GameControlador buttonControlador = new GameControlador(gameWindow,mineSweeperBoard);
        gameWindow.setControlador(buttonControlador);
    }

}
