package gui.SetSize;

import manager.Jukebox;
import manager.ResourceManager;
import manager.Theme;

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
    public static final String TOGGLESOUND = "SOUND";

    private final ISetSizeWindow window;
    private final Semaphore semaphore;
    private int xSize;
    private int ySize;
    private int mines;

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
                    Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.MENU_START_SOUND));
                    xSize = 16;
                    ySize = 16;
                    mines = 40;
                    window.dispose();
                    semaphore.release();
                    break;
                case EIGHT:
                    Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.MENU_START_SOUND));
                    xSize = 8;
                    ySize = 8;
                    mines = 10;
                    window.dispose();
                    semaphore.release();
                    break;
                case CUSTOM:
                    Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.MENU_START_SOUND));
                    xSize = window.getxSize();
                    ySize = window.getySize();
                    mines = (int) Math.round(xSize * ySize / 6.4);
                    window.dispose();
                    semaphore.release();
                    break;
                case TOGGLESOUND:
                    Jukebox.toggleSoud();
                    window.setMenuSoundToggle(Jukebox.canPlayMusic());
                default:
                    if (command.contains(Theme.THEME_MANAGER_PACKAGE_NAME)) {
                        Theme.setTheme(command);
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

