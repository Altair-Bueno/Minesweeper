package MineSweeperGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class SetSizeControlador implements ActionListener {
    public static final String GO="GO";

    private ISetSizeWindow window;
    private int xSize;
    private int ySize;
    private Semaphore semaphore;

    public SetSizeControlador(ISetSizeWindow window, Semaphore semaphore) {
        this.window = window;
        this.semaphore = semaphore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if (command==GO) {
            xSize=window.getxSize();
            ySize=window.getySize();
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
}
