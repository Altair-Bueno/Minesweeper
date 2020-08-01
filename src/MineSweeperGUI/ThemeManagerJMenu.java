package MineSweeperGUI;

import MineSweeperJavaResources.MineSweeperLanguageManager;
import MineSweeperJavaResources.MineSweeperResourceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import static MineSweeperJavaResources.ThemeManager.*;


public class ThemeManagerJMenu extends JMenu {
    //Elementos del menu
    private JMenuItem light;
    private JMenuItem dracula;
    private JMenuItem morado;

    public ThemeManagerJMenu(){
        super("Tema");
        light=new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Claro"));
        light.setActionCommand(CLARO);
        dracula=new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Oscuro"));
        dracula.setActionCommand(DRACULA);
        morado=new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("Morado"));
        morado.setActionCommand(MORADO);

        if(System.getProperty("os.name").toLowerCase().contains("mac")) {
            light.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
            dracula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
            morado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
        } else {
            light.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.META_MASK));
            dracula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.META_MASK));
            morado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.META_MASK));

        }
        add(light);
        add(dracula);
        add(morado);
    }

    public void setActionListener(ActionListener controlador){
        light.addActionListener(controlador);
        dracula.addActionListener(controlador);
        morado.addActionListener(controlador);
    }
}
