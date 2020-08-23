package MineSweeperGUI.Others;

import MineSweeperResources.MineSweeperLanguageManager;
import MineSweeperResources.MineSweeperPlatformManager;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static MineSweeperResources.ThemeManager.*;


public class ThemeManagerJMenu extends JMenu {
    /*
    Conects the ThemeManager with the JFrame
     */
    private final JMenuItem light;
    private final JMenuItem dracula;
    private final JMenuItem purple;

    public ThemeManagerJMenu() {
        super(MineSweeperLanguageManager.getResourceBundle().getString("Theme"));
        light = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Claro"));
        light.setActionCommand(LIGHT);
        dracula = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Oscuro"));
        dracula.setActionCommand(DRACULA);
        purple = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Morado"));
        purple.setActionCommand(PURPLE);


        light.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        dracula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        purple.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, MineSweeperPlatformManager.getMainKeyboardActionEvent()));

        add(light);
        add(dracula);
        add(purple);
    }

    public void setActionListener(ActionListener listener) {
        light.addActionListener(listener);
        dracula.addActionListener(listener);
        purple.addActionListener(listener);
    }
}
