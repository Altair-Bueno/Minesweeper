package MineSweeperGUI.SetSize;

import MineSweeperResources.MineSweeperJukeBox;
import MineSweeperResources.MineSweeperResourceManager;
import MineSweeperResources.ThemeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class SetSizeListener implements ActionListener {
    /*
    A SetSizeListener instance is in charge of ActionEvents on the SelectSizeWindow
    */
    public static final String CUSTOM = "CUSTOM";
    public static final String EIGHT = "EIGHT";
    public static final String SIXTEEN = "SIXTEEN";

    private final ISetSizeWindow window;
    private int xSize;
    private int ySize;
    private int mines;
    private final Semaphore semaphore;

    public SetSizeListener(ISetSizeWindow window, Semaphore semaphore) {
        this.window = window;
        this.semaphore = semaphore;
        mines = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            switch (command) {
                case SIXTEEN:
                    MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MENU_START_SOUND));
                    xSize = 16;
                    ySize = 16;
                    mines = 40;
                    window.dispose();
                    semaphore.release();
                    break;
                case EIGHT:
                    MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MENU_START_SOUND));
                    xSize = 8;
                    ySize = 8;
                    mines = 10;
                    window.dispose();
                    semaphore.release();
                    break;
                case CUSTOM:
                    MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MENU_START_SOUND));
                    xSize = window.getxSize();
                    ySize = window.getySize();
                    mines = (int) Math.round(xSize * ySize / 6.4);
                    window.dispose();
                    semaphore.release();
                    break;
                default:
                    if (command.contains(ThemeManager.THEME_MANAGER_PACKAGE_NAME)) {
                        ThemeManager.setTheme(command);
                        window.updateComponentTree();
                    }
            }
        } catch (Exception o) {
            JOptionPane.showMessageDialog(null, o.getMessage());
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

