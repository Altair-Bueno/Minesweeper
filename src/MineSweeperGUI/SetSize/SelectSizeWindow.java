package MineSweeperGUI.SetSize;

import MineSweeperLogic.MineSweeperBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectSizeWindow extends JFrame implements ISetSizeWindow {

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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperBoard.APPNAME);
        setName(MineSweeperBoard.APPNAME);

        if (!System.getProperty("os.name").contains("Mac")) {
            try {
                setIconImage(ImageIO.read(SelectSizeWindow.class.getResourceAsStream("/" + MineSweeperBoard.ICON)));
            } catch (Exception e) {
                setIconImage(new ImageIcon(MineSweeperBoard.ICON).getImage());
            }
        }

        pack();
        setLocationRelativeTo(null);
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

        play8x8.setActionCommand(SetSizeControlador.EIGHT);
        play16x16.setActionCommand(SetSizeControlador.SIXTEEN);
        playCustom.setActionCommand(SetSizeControlador.CUSTOM);
    }
}
