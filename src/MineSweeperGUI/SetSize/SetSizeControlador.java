package MineSweeperGUI.SetSize;

import MineSweeperGUI.SetSize.ISetSizeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class SetSizeControlador implements ActionListener {

    public static final String CUSTOM="CUSTOM";
    public static final String EIGHT="EIGHT";
    public static final String SIXTEEN="SIXTEEN";

    private ISetSizeWindow window;
    private int xSize;
    private int ySize;
    private int mines;
    private Semaphore semaphore;

    public SetSizeControlador(ISetSizeWindow window, Semaphore semaphore) {
        this.window = window;
        this.semaphore = semaphore;
        mines=0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if (command.equals(SIXTEEN)) {
            xSize=16;
            ySize=16;
            mines=40;
            window.dispose();
            semaphore.release();
        }else if (command.equals(EIGHT)) {
            xSize=8;
            ySize=8;
            mines=10;
            window.dispose();
            semaphore.release();
        } else if (command.equals(CUSTOM)) {
            xSize=window.getxSize();
            ySize=window.getySize();
            mines= (int) Math.round (xSize*ySize/6.4);
            window.dispose();
            semaphore.release();
        }
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public int getMines() {
        return mines;
    }
}
