package MineSweeperGUI.Others;

import MineSweeperResources.MineSweeperLanguageManager;
import MineSweeperResources.MineSweeperPlatformManager;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static MineSweeperResources.ThemeManager.*;


public class ThemeManagerJMenu extends JMenu {
    //Elementos del menu
    private final JMenuItem light;
    private final JMenuItem dracula;
    private final JMenuItem morado;

    public ThemeManagerJMenu() {
        super(MineSweeperLanguageManager.getResourceBundle().getString("Theme"));
        light = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Claro"));
        light.setActionCommand(CLARO);
        dracula = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Oscuro"));
        dracula.setActionCommand(DRACULA);
        morado = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Morado"));
        morado.setActionCommand(MORADO);


        light.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        dracula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        morado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, MineSweeperPlatformManager.getMainKeyboardActionEvent()));

        add(light);
        add(dracula);
        add(morado);
    }

    public void setActionListener(ActionListener controlador) {
        light.addActionListener(controlador);
        dracula.addActionListener(controlador);
        morado.addActionListener(controlador);
    }
}
