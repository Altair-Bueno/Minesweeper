package MineSweeperGUI.SetSize;

import MineSweeperGUI.ThemeManagerJMenu;
import MineSweeperJavaResources.MineSweeperPlatformManager;
import MineSweeperJavaResources.MineSweeperResourceManager;

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
    private JSpinner xSize;
    private JSpinner ySize;
    private JLabel X;
    private JPanel playJPanel8;
    private JPanel playJPanel16;

    private JLabel gameIcon;
    private JPanel iconJpanel;

    private final JMenuBar jMenuBar;
    private final ThemeManagerJMenu themeManagerJMenu;

    public SelectSizeWindow() {
        add(rootPanel);

        xSize.setModel(new SpinnerNumberModel(1, 1, 128, 1));
        ySize.setModel(new SpinnerNumberModel(1, 1, 128, 1));

        gameIcon.setIcon(MineSweeperResourceManager.getSmallAppIcon());

        jMenuBar = new JMenuBar();
        themeManagerJMenu = new ThemeManagerJMenu();
        jMenuBar.add(themeManagerJMenu);
        setJMenuBar(jMenuBar);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperResourceManager.getAPPNAME());
        setName(MineSweeperResourceManager.getAPPNAME());

        if (!MineSweeperPlatformManager.isHostOSMac()) {
            setIconImage(MineSweeperResourceManager.getAppIcon().getImage());
        }

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public int getxSize() {
        return (Integer) xSize.getValue();
    }

    @Override
    public int getySize() {
        return (Integer) ySize.getValue();
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
