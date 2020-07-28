import MineSweeperGUI.ISetSizeWindow;
import MineSweeperGUI.SelectSizeWindow;
import MineSweeperGUI.SetSizeControlador;
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

        semaphore.acquire();

        int xSize= setSizeControlador.getxSize();
        int ySize= setSizeControlador.getySize();


    }

}
