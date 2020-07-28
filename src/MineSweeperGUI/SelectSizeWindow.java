package MineSweeperGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectSizeWindow extends JFrame implements ISetSizeWindow{

    private JPanel rootPanel;
    private JTabbedPane tabbedPane;
    private JPanel MineSweeper8x8;
    private JPanel MineSweeper16x16;
    private JPanel customMineSweeper;
    private JButton play8x8;
    private JButton play16x16;
    private JPanel customJPanel;
    private JButton playCustom;
    private JPanel valuesJPanel;
    private JPanel playCustomPanel;
    private JComboBox xSize;
    private JComboBox ySize;

    public SelectSizeWindow() {
        add(rootPanel);
        setVisible(true);
    }

    @Override
    public int getxSize() {
        return xSize.getSelectedIndex()+1;
    }

    @Override
    public int getySize() {
        return ySize.getSelectedIndex()+1;
    }

    @Override
    public void setControlador(ActionListener actionListener) {
        play8x8.addActionListener(actionListener);
        play16x16.addActionListener(actionListener);
        playCustom.addActionListener(actionListener);


    }
}
