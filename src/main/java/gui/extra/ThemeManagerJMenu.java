package gui.extra;

import manager.Language;
import manager.Platform;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static manager.Theme.*;


public class ThemeManagerJMenu extends JMenu {
    /*
    Conects the ThemeManager with the JFrame
     */
    private final JMenuItem light;
    private final JMenuItem dracula;
    private final JMenuItem purple;

    public ThemeManagerJMenu() {
        super(Language.getResourceBundle().getString("Theme"));
        light = new JMenuItem(Language.getResourceBundle().getString("Claro"));
        light.setActionCommand(LIGHT);
        dracula = new JMenuItem(Language.getResourceBundle().getString("Oscuro"));
        dracula.setActionCommand(DRACULA);
        purple = new JMenuItem(Language.getResourceBundle().getString("Morado"));
        purple.setActionCommand(PURPLE);


        light.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, Platform.getMainKeyboardActionEvent()));
        dracula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, Platform.getMainKeyboardActionEvent()));
        purple.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, Platform.getMainKeyboardActionEvent()));

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
