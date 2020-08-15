package MineSweeperGUI.SetSize;

import MineSweeperGUI.Others.HelpJMenu;
import MineSweeperGUI.Others.ThemeManagerJMenu;
import MineSweeperJavaResources.MineSweeperPlatformManager;
import MineSweeperJavaResources.MineSweeperResourceManager;
import MineSweeperJavaResources.MineSweeperScoreboard;

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
    private JList scoreboardList;
    private JPanel scoreboardJpanel;

    private static int lastGamePanel=0;
    private static int xSizeLastValue=8;
    private static int ySizeLastValue=8;

    private final ThemeManagerJMenu themeManagerJMenu;

    public SelectSizeWindow() {
        add(rootPanel);

        xSize.setModel(new SpinnerNumberModel(xSizeLastValue, 8, 21, 1));
        ySize.setModel(new SpinnerNumberModel(ySizeLastValue, 8, 21, 1));

        tabbedPane.setSelectedIndex(lastGamePanel);


        scoreboardList.setListData(MineSweeperScoreboard.getScoreboard());

        gameIcon.setIcon(MineSweeperResourceManager.getSmallAppIcon());

        JMenuBar jMenuBar = new JMenuBar();
        themeManagerJMenu = new ThemeManagerJMenu();
        jMenuBar.add(themeManagerJMenu);
        jMenuBar.add(new HelpJMenu());

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

    @Override
    public void dispose() {
        lastGamePanel=tabbedPane.getSelectedIndex();
        xSizeLastValue=getxSize();
        ySizeLastValue=getySize();
        super.dispose();
    }
}
