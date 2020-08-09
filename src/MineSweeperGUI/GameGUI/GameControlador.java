package MineSweeperGUI.GameGUI;

import MineSweeperJavaResources.MineSweeperLanguageManager;
import MineSweeperJavaResources.MineSweeperResourceManager;
import MineSweeperJavaResources.StartMineSweeper;
import MineSweeperJavaResources.ThemeManager;
import MineSweeperLogic.Coordenada;
import MineSweeperLogic.GameOver;
import MineSweeperLogic.MineSweeperBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControlador implements ActionListener, MouseListener {

    public static final String NEW = "NEW";

    private final IGameWindow window;
    private final MineSweeperBoard board;

    public GameControlador(IGameWindow window, MineSweeperBoard board) {
        this.window = window;
        this.board = board;
        window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber() + "</b></font></html>");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        if (comand.toLowerCase().contains(ThemeManager.THEME_MANAGER_PACKAGE_NAME)) {
            ThemeManager.setTheme(comand);
            window.updateJFrameTheme();
        } else if (comand.equals(NEW)) {
            Thread game = new Thread(new StartMineSweeper());
            game.start();
            window.dispose();
        } else {
            try {
                Coordenada coordenada = Coordenada.parseString(comand);
                board.dig(coordenada);
                window.setVisibility(board.getVisibility(), board.getTablero());
                board.checkWin();
            } catch (GameOver over) {
                gameOver(over);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            BoxJButton button = (BoxJButton) e.getComponent();
            if (!button.isDigged()) {
                if (button.getIcon() != null) {
                    button.setIcon(null);
                    button.setFlagged(false);
                    board.addFlag();
                } else {
                    button.setIcon(MineSweeperResourceManager.getFlagIcon());
                    button.setFlagged(true);
                    board.removeFlag();
                }
                window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber() + "</b></font></html>");
            }
            try {
                board.checkWin();
            } catch (GameOver over) {
                gameOver(over);
            }
        }
    }

    private void gameOver(GameOver over) {
        window.stopClock();
        String [] options= {MineSweeperLanguageManager.getResourceBundle().getString("Yes"),MineSweeperLanguageManager.getResourceBundle().getString("No")};

        if (over.getGameOverCode() == GameOver.GAMEWON) {
            int i = JOptionPane.showOptionDialog(
                    (JFrame) window, MineSweeperLanguageManager.getResourceBundle().getString("Play_again"),  MineSweeperLanguageManager.getResourceBundle().getString("Win_message"),
                    JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    MineSweeperResourceManager.getConffetiIcon(),
                    options,null);
            if (i == JOptionPane.YES_OPTION) {
                Thread game = new Thread(new StartMineSweeper());
                game.start();
                window.dispose();
            } else {
                System.exit(0);
            }
        } else if (over.getGameOverCode() == GameOver.MINEFOUND) {
            int i = JOptionPane.showOptionDialog(
                    (JFrame) window, MineSweeperLanguageManager.getResourceBundle().getString("Play_again"), MineSweeperLanguageManager.getResourceBundle().getString("Mine_message"),
                    JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    MineSweeperResourceManager.getExplosionIcon(),
                    options,null);
            if (i == JOptionPane.YES_OPTION) {
                Thread game = new Thread(new StartMineSweeper());
                game.start();
                window.dispose();
            } else {
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
