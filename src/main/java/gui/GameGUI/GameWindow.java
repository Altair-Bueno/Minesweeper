package gui.GameGUI;

import gui.Others.HelpJMenu;
import gui.Others.ThemeManagerJMenu;
import board.Coordinate;
import board.MineSweeperBoard;
import board.Score;
import manager.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class GameWindow extends JFrame implements IGameWindow, Runnable {

    private final Map<Coordinate, BoxJButton> gameButtonsMap;
    private final ThemeManagerJMenu themeManagerJMenu;
    private final JMenuItem newJMenu;
    private final JCheckBoxMenuItem toggleSound;
    private JPanel rootPane;
    private JPanel statusPanel;
    private JLabel flagNumberJLabel;
    private JPanel gameButtons;
    //private GameListener controlador;
    private JLabel time;
    private int clockTime;
    private boolean clockIsStopped;

    public GameWindow(int rows, int columns) {
        add(rootPane);
        flagNumberJLabel.setIcon(new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.FLAGICON)));
        time.setIcon(new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.CLOCKICON)));

        //JMenubar code
        JMenuBar jMenuBar = new JMenuBar();
        themeManagerJMenu = new ThemeManagerJMenu();
        JMenu game = new JMenu(Language.getResourceBundle().getString("Game"));
        newJMenu = new JMenuItem(Language.getResourceBundle().getString("New_Game"));
        newJMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Platform.getMainKeyboardActionEvent()));
        toggleSound = new JCheckBoxMenuItem(Language.getResourceBundle().getString("ToggleSound"));
        toggleSound.setState(Jukebox.canPlayMusic());

        jMenuBar.add(game);
        jMenuBar.add(themeManagerJMenu);
        game.add(newJMenu);
        game.add(toggleSound);
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
                tempButton.setBackground(Theme.getUndiggedBackground());

                gameButtonsMap.put(coordinate, tempButton);
                gameButtons.add(tempButton);
            }
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(ResourceManager.getAPPNAME());
        setName(ResourceManager.getAPPNAME());

        //JFrame icon
        if (!Platform.isHostOSMac())
            setIconImage(new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.APPICON)).getImage());

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
                e.getComponent().setBounds(b.x, b.y, b.width, b.width * H / W);
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
        toggleSound.addActionListener(listener);
        themeManagerJMenu.setActionListener(listener);

        newJMenu.addActionListener(listener);
        newJMenu.setActionCommand(GameListener.NEW);
        toggleSound.setActionCommand(GameListener.TOGGLESOUND);
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
                Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.DIG_SOUND));
            }
            button.setDigged(true);
            button.setBackground(Theme.getDiggedBackground());
            int value = changedVisibility.get(coordinate);
            button.setValue(value);
            if (value == MineSweeperBoard.MINE) {
                button.setIcon(new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.MINEICON)));
            } else if (value > MineSweeperBoard.EMPTY) {
                button.setText("<html><b><font size=5 color=" + Theme.getFontColors()[value] + ">" + value + "</font></b></html>");
            }
        }

    }

    @Override
    public void updateJFrameTheme() {
        SwingUtilities.updateComponentTreeUI(this);
        for (BoxJButton button : gameButtonsMap.values()) {
            Color temp = button.isDigged() ?
                    Theme.getDiggedBackground() : Theme.getUndiggedBackground();
            button.setBackground(temp);

            Integer value = button.getValue();
            if (button.isDigged() && value != null && value > 0) {
                button.setText("<html><b><font size=5 color=" + Theme.getFontColors()[value] + ">" + value + "</font></b></html>");
            }
        }
    }

    @Override
    public int stopClock() {
        clockIsStopped = true;
        return clockTime;
    }

    @Override
    public void setMenuSoundToggle(boolean state) {
        toggleSound.setState(state);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPane = new JPanel();
        rootPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        statusPanel = new JPanel();
        statusPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPane.add(statusPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        flagNumberJLabel = new JLabel();
        flagNumberJLabel.setText("Label");
        statusPanel.add(flagNumberJLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        time = new JLabel();
        time.setText("Label");
        statusPanel.add(time, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gameButtons = new JPanel();
        gameButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, true, true));
        rootPane.add(gameButtons, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPane;
    }
}