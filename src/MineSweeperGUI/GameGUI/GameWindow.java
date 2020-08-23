package MineSweeperGUI.GameGUI;

import MineSweeperGUI.Others.HelpJMenu;
import MineSweeperGUI.Others.ThemeManagerJMenu;
import MineSweeperLogic.Coordinate;
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

    private final Map<Coordinate, BoxJButton> gameButtonsMap;
    //private GameListener controlador;

    private final ThemeManagerJMenu themeManagerJMenu;
    private final JMenuItem newJMenu;

    public GameWindow(int rows, int columns) {
        add(rootPane);
        flagNumberJLabel.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.FLAGICON)));
        time.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.CLOCKICON)));

        //JMenubar code
        JMenuBar jMenuBar = new JMenuBar();
        themeManagerJMenu = new ThemeManagerJMenu();
        JMenu game = new JMenu(MineSweeperLanguageManager.getResourceBundle().getString("Game"));
        newJMenu = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("New_Game"));
        newJMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, MineSweeperPlatformManager.getMainKeyboardActionEvent()));
        jMenuBar.add(themeManagerJMenu);
        jMenuBar.add(game);
        game.add(newJMenu);
        jMenuBar.add(new HelpJMenu());
        setJMenuBar(jMenuBar);

        gameButtonsMap = new HashMap<>(rows * columns);
        gameButtons.setLayout(new GridLayout(rows, columns));

        Dimension screenSize = getToolkit().getScreenSize();
        int size = screenSize.width < screenSize.height ? screenSize.width / columns : screenSize.height / rows;
        size = (int) Math.round(size * 0.6);
        Dimension dimension = new Dimension(size, size);

        //Start the game buttons
        for (int i = 0; i < rows; i++) {
            for (int u = 0; u < columns; u++) {
                Coordinate coordinate = new Coordinate(i, u);
                BoxJButton tempButton = new BoxJButton(coordinate);
                tempButton.setFocusPainted(false);
                tempButton.setActionCommand(i + ":" + u);
                tempButton.setMinimumSize(dimension);
                tempButton.setPreferredSize(dimension);
                tempButton.setMaximumSize(dimension);
                tempButton.setBackground(ThemeManager.getUndiggedBackground());

                gameButtonsMap.put(coordinate, tempButton);
                gameButtons.add(tempButton);
            }
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperResourceManager.getAPPNAME());
        setName(MineSweeperResourceManager.getAPPNAME());

        //JFrame icon
        if (!MineSweeperPlatformManager.isHostOSMac())
            setIconImage(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.APPICON)).getImage());

        //Start the clock
        Thread clockThread = new Thread(this);
        clockThread.start();

        //Keep the minesweeper ui aspect ratio to 1:1
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
    public void setListener(GameListener listener) {
        //this.listener = listener;

        for (JButton button : gameButtonsMap.values()) {
            button.addActionListener(listener);
            button.addMouseListener(listener);
        }

        themeManagerJMenu.setActionListener(listener);

        newJMenu.addActionListener(listener);
        newJMenu.setActionCommand(GameListener.NEW);
    }

    @Override
    public void setStatusPanel(String status) {
        flagNumberJLabel.setText(status);
    }

    @Override
    public void setVisibility(Map<Coordinate, Integer> changedVisibility) {
        boolean hasPlayedSound = false;

        for (Coordinate coordinate : changedVisibility.keySet()) {

            BoxJButton button = gameButtonsMap.get(coordinate);
            if (!hasPlayedSound) {
                hasPlayedSound = true;
                MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.DIG_SOUND));
            }
            button.setDigged(true);
            button.setBackground(ThemeManager.getDiggedBackground());
            int value = changedVisibility.get(coordinate);
            button.setValue(value);
            if (value == MineSweeperBoard.MINE) {
                button.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MINEICON)));
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

    //Game's clock code
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
