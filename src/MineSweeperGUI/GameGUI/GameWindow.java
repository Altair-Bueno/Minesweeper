package MineSweeperGUI.GameGUI;

import MineSweeperGUI.ThemeManagerJMenu;
import MineSweeperJavaResources.MineSweeperLanguageManager;
import MineSweeperJavaResources.MineSweeperPlatformManager;
import MineSweeperJavaResources.MineSweeperResourceManager;
import MineSweeperJavaResources.ThemeManager;
import MineSweeperLogic.MineSweeperBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWindow extends JFrame implements IGameWindow, Runnable {

    private JPanel rootPane;
    private JPanel statusPanel;
    private JLabel flagNumberJLabel;
    private JPanel gameButtons;
    private JLabel time;

    private int clockTime;
    private boolean clockIsStopped;

    private final List<BoxJButton> gameButtonslist;
    private GameControlador controlador;

    private final ThemeManagerJMenu themeManagerJMenu;
    private final JMenuItem nuevo;

    public GameWindow(int filas, int columnas) {
        add(rootPane);
        flagNumberJLabel.setIcon(MineSweeperResourceManager.getFlagIcon());
        time.setIcon(MineSweeperResourceManager.getClockIcon());

        JMenuBar jMenuBar = new JMenuBar();
        themeManagerJMenu = new ThemeManagerJMenu();
        JMenu archivo = new JMenu(MineSweeperLanguageManager.getResourceBundle().getString("Game"));
        nuevo = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("New_Game"));
        nuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        jMenuBar.add(themeManagerJMenu);
        jMenuBar.add(archivo);
        archivo.add(nuevo);

        setJMenuBar(jMenuBar);

        gameButtonslist = new ArrayList<>(filas * columnas);
        gameButtons.setLayout(new GridLayout(filas, columnas));

        int resolution = getToolkit().getScreenResolution();

        //int tam=(int) Math.round(resolution * 0.25);
        int tam =(int) Math.round(resolution * 0.30);
        Dimension dimension = new Dimension(tam, tam);

        for (int i = 0; i < filas; i++) {
            for (int u = 0; u < columnas; u++) {
                BoxJButton tempButton = new BoxJButton();
                tempButton.setFocusPainted(false);
                tempButton.setActionCommand(i + ":" + u);
                tempButton.setMinimumSize(dimension);
                tempButton.setPreferredSize(dimension);
                tempButton.setMaximumSize(dimension);
                tempButton.setBackground(ThemeManager.getUndiggedBackground());
                gameButtonslist.add(tempButton);
                gameButtons.add(tempButton);
            }
        }


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperResourceManager.getAPPNAME());
        setName(MineSweeperResourceManager.getAPPNAME());

        if (!MineSweeperPlatformManager.isHostOSMac())
            setIconImage(MineSweeperResourceManager.getAppIcon().getImage());

        Thread clockThread = new Thread(this);
        clockThread.start();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setControlador(GameControlador controlador) {
        this.controlador = controlador;

        for (JButton button : gameButtonslist) {
            button.addActionListener(controlador);
            button.addMouseListener(controlador);
        }

        themeManagerJMenu.setActionListener(controlador);

        nuevo.addActionListener(controlador);
        nuevo.setActionCommand(GameControlador.NEW);
    }

    @Override
    public void setStatusPanel(String status) {
        flagNumberJLabel.setText(status);
    }

    @Override
    public void setVisibility(boolean[][] visibility, int[][] values) {
        Iterator<BoxJButton> iterator = gameButtonslist.iterator();
        for (int i = 0; i < visibility.length; i++) {
            for (int u = 0; u < visibility[0].length; u++) {

                BoxJButton button = iterator.next();

                if (visibility[i][u] && !button.isDigged() && !button.isFlagged()) {
                    button.removeActionListener(controlador);
                    button.setDigged(true);
                    button.setBackground(ThemeManager.getDiggedBackground());

                    if (values[i][u] == MineSweeperBoard.MINA) {
                        button.setIcon(MineSweeperResourceManager.getMinaIcon());
                    } else if (values[i][u] > 0) {
                        button.setText("<html><b><font size=5 color=" + ThemeManager.getFontColors()[values[i][u]] + ">" + values[i][u] + "</font></b></html>");
                    }
                }
            }
        }
    }

    @Override
    public void updateJFrameTheme() {
        SwingUtilities.updateComponentTreeUI(this);
        for (BoxJButton button : gameButtonslist) {
            Color temp = button.isDigged() ?
                    ThemeManager.getDiggedBackground() : ThemeManager.getUndiggedBackground();
            button.setBackground(temp);
        }
    }

    @Override
    public void stopClock() {
        clockIsStopped = true;
    }

    @Override
    public int getPuntuation() {
        return clockTime;
    }


    @Override
    public void run() {
        clockTime = 0;
        clockIsStopped = false;
        while (!clockIsStopped) {
            time.setText("<html><b><font size=5>" + clockTime +
                    "</b></font></html>");
            clockTime++;
            try {
                Thread.sleep(1000); //Wait 1 second
            } catch (Exception ignored) {
            }
        }
    }
}
