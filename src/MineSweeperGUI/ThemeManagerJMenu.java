package MineSweeperGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static MineSweeperGUI.ThemeManager.*;


public class ThemeManagerJMenu extends JMenu {


    //Elementos del menu
    private JMenuItem light;
    private JMenuItem dracula;
    private JMenuItem morado;

    public ThemeManagerJMenu(){
        super("Tema");
        light=new JMenuItem("Claro");
        light.setActionCommand(CLARO);
        dracula=new JMenuItem("Oscuro");
        dracula.setActionCommand(DRACULA);
        morado=new JMenuItem("Morado");
        morado.setActionCommand(MORADO);

        if(System.getProperty("os.name").contains("mac")) {
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
