package MineSweeperGUI.GameGUI;

import MineSweeperGUI.Others.HelpJMenu;
import MineSweeperGUI.Others.ThemeManagerJMenu;
import MineSweeperLogic.Coordenada;
import MineSweeperLogic.MineSweeperBoard;
import MineSweeperLogic.Score;
import MineSweeperResources.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class GameWindow extends JFrame implements IGameWindow, Runnable {

    private JPanel rootPane;
    private JPanel statusPanel;
    private JLabel flagNumberJLabel;
    private JPanel gameButtons;
    private JLabel time;

    private int clockTime;
    private boolean clockIsStopped;

    private final Map<Coordenada, BoxJButton> gameButtonsMap;
    //private GameControlador controlador;

    private final ThemeManagerJMenu themeManagerJMenu;
    private final JMenuItem nuevo;

    public GameWindow(int filas, int columnas) {
        add(rootPane);
        flagNumberJLabel.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.FLAGICON)));
        time.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.CLOCKICON)));

        JMenuBar jMenuBar = new JMenuBar();
        themeManagerJMenu = new ThemeManagerJMenu();
        JMenu archivo = new JMenu(MineSweeperLanguageManager.getResourceBundle().getString("Game"));
        nuevo = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("New_Game"));
        nuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        jMenuBar.add(themeManagerJMenu);
        jMenuBar.add(archivo);
        archivo.add(nuevo);

        jMenuBar.add(new HelpJMenu());

        setJMenuBar(jMenuBar);

        gameButtonsMap = new HashMap<>(filas * columnas);
        gameButtons.setLayout(new GridLayout(filas, columnas));

        int resolution = getToolkit().getScreenResolution();
        Dimension screenSize = getToolkit().getScreenSize();

        //int tam=(int) Math.round(resolution * 0.25);
        //int tam = (columnas*filas)/resolution;
        /*
        int tam = (int) Math.round(resolution * 0.30);
        Dimension dimension = new Dimension(tam, tam);
        */
        int size = 0;
        size = screenSize.width < screenSize.height ? screenSize.width / columnas : screenSize.height / filas;
        size = (int) Math.round(size * 0.6);
        Dimension dimension = new Dimension(size, size);

        for (int i = 0; i < filas; i++) {
            for (int u = 0; u < columnas; u++) {
                Coordenada coordenada = new Coordenada(i, u);
                BoxJButton tempButton = new BoxJButton(coordenada);
                tempButton.setFocusPainted(false);
                tempButton.setActionCommand(i + ":" + u);
                tempButton.setMinimumSize(dimension);
                tempButton.setPreferredSize(dimension);
                tempButton.setMaximumSize(dimension);
                tempButton.setBackground(ThemeManager.getUndiggedBackground());

                gameButtonsMap.put(coordenada, tempButton);
                gameButtons.add(tempButton);
            }
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperResourceManager.getAPPNAME());
        setName(MineSweeperResourceManager.getAPPNAME());

        if (!MineSweeperPlatformManager.isHostOSMac())
            setIconImage(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.APPICON)).getImage());

        Thread clockThread = new Thread(this);
        clockThread.start();

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int W = 1;
                int H = 1;
                Rectangle b = e.getComponent().getBounds();
                e.getComponent().setBounds(b.x, b.y, b.width, b.width*H/W);
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setControlador(GameControlador controlador) {
        //this.controlador = controlador;

        for (JButton button : gameButtonsMap.values()) {
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

    /*
        @Override
        public void setVisibility(boolean[][] visibility, int[][] values) {
            boolean hasPlayedSoud=false;
            Iterator<BoxJButton> iterator = gameButtonslist.iterator();
            for (int i = 0; i < visibility.length; i++) {
                for (int u = 0; u < visibility[0].length; u++) {

                    BoxJButton button = iterator.next();

                    if (visibility[i][u] && !button.isDigged() && !button.isFlagged()) {
                        //button.removeActionListener(controlador);
                        button.setDigged(true);
                        button.setBackground(ThemeManager.getDiggedBackground());
                        int value = values[i][u];
                        button.setValue(value);

                        if (!hasPlayedSoud){
                            hasPlayedSoud=true;
                            MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.DIG_SOUND));
                        }

                        if (values[i][u] == MineSweeperBoard.MINA) {
                            button.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MINAICON)));
                        } else if (values[i][u] > 0) {
                            button.setText("<html><b><font size=5 color=" + ThemeManager.getFontColors()[value] + ">" + value + "</font></b></html>");
                        }
                    }
                }
            }
        }

     */
    @Override
    public void setVisibility(Map<Coordenada, Integer> changedVisibility) {
        boolean hasPlayedSound = false;

        for (Coordenada coordenada : changedVisibility.keySet()) {

            BoxJButton button = gameButtonsMap.get(coordenada);
            if (!hasPlayedSound) {
                hasPlayedSound = true;
                MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.DIG_SOUND));
            }
            button.setDigged(true);
            button.setBackground(ThemeManager.getDiggedBackground());
            int value = changedVisibility.get(coordenada);
            button.setValue(value);
            if (value == MineSweeperBoard.MINA) {
                button.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MINAICON)));
            } else if (value > MineSweeperBoard.EMPTY) {
                button.setText("<html><b><font size=5 color=" + ThemeManager.getFontColors()[value] + ">" + value + "</font></b></html>");
            }
        }

    }

    @Override
    public void updateJFrameTheme() {
        SwingUtilities.updateComponentTreeUI(this);
        for (BoxJButton button : gameButtonsMap.values()) {
            Color temp = button.isDigged() ?
                    ThemeManager.getDiggedBackground() : ThemeManager.getUndiggedBackground();
            button.setBackground(temp);

            Integer value = button.getValue();
            if (button.isDigged() && value != null && value > 0) {
                button.setText("<html><b><font size=5 color=" + ThemeManager.getFontColors()[value] + ">" + value + "</font></b></html>");
            }
        }
    }

    @Override
    public int stopClock() {
        clockIsStopped = true;
        return clockTime;
    }


    @Override
    public void run() {
        clockTime = 0;
        clockIsStopped = false;
        while (!clockIsStopped) {
            time.setText("<html><b><font size=5>" + new Score(clockTime) +
                    "</b></font></html>");
            clockTime++;
            try {
                Thread.sleep(1000); //Wait 1 second
            } catch (Exception ignored) {
            }
        }
    }
}
