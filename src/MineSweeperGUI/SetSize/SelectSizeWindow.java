package MineSweeperGUI.SetSize;

import MineSweeperGUI.ThemeManagerJMenu;
import MineSweeperLogic.MineSweeperBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    private JTextField xSize;
    private JTextField ySize;
    private JLabel X;
    private JPanel playJPanel8;
    private JPanel playJPanel16;

    private JLabel gameIcon;
    private JPanel iconJpanel;

    private JMenuBar jMenuBar;
    private ThemeManagerJMenu themeManagerJMenu;

    public SelectSizeWindow() {
        add(rootPanel);

        try{
            gameIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("/res/smallIcon.png")));
        }catch (Exception e){
            gameIcon.setIcon(new ImageIcon("res/smallIcon.png"));
        }

        jMenuBar=new JMenuBar();
        themeManagerJMenu=new ThemeManagerJMenu();
        jMenuBar.add(themeManagerJMenu);
        setJMenuBar(jMenuBar);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperBoard.APPNAME);
        setName(MineSweeperBoard.APPNAME);

        if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
            try {
                setIconImage(ImageIO.read(SelectSizeWindow.class.getResourceAsStream("/" + MineSweeperBoard.ICON)));
            } catch (Exception e) {
                setIconImage(new ImageIcon(MineSweeperBoard.ICON).getImage());
            }
        }

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public int getxSize() {
        int size=Integer.parseInt(xSize.getText());
        if (size<=0) throw new RuntimeException("Valor negativo");
        return size;
    }

    @Override
    public int getySize() {
        int size=Integer.parseInt(ySize.getText());
        if (size<=0) throw new RuntimeException("Valor negativo");
        return size;
    }

    @Override
    public void setControlador(ActionListener actionListener) {
        play8x8.addActionListener(actionListener);
        play16x16.addActionListener(actionListener);
        playCustom.addActionListener(actionListener);

        play8x8.setActionCommand(SetSizeControlador.EIGHT);
        play16x16.setActionCommand(SetSizeControlador.SIXTEEN);
        playCustom.setActionCommand(SetSizeControlador.CUSTOM);

        themeManagerJMenu.setActionListener(actionListener);
    }

    @Override
    public void updateComponentTree() {
        SwingUtilities.updateComponentTreeUI(this);
        pack();
    }
}
