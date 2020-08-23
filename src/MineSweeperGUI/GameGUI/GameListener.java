package MineSweeperGUI.GameGUI;

import MineSweeperLogic.Coordinate;
import MineSweeperLogic.GameOver;
import MineSweeperLogic.MineSweeperBoard;
import MineSweeperResources.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameListener implements ActionListener, MouseListener {
    /*
    A GameListener instance is in charge of ActionEvents and MouseEvents on the main game window
     */
    public static final String NEW = "NEW";

    private final IGameWindow window;
    private final MineSweeperBoard board;

    public GameListener(IGameWindow window, MineSweeperBoard board) {
        this.window = window;
        this.board = board;
        window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber() + "</b></font></html>");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        if (comand.toLowerCase().contains(ThemeManager.THEME_MANAGER_PACKAGE_NAME)) {
            //Theme management code
            ThemeManager.setTheme(comand);
            window.updateJFrameTheme();
        } else if (comand.equals(NEW)) {
            //Terminates this thread and creates another game thread
            Thread game = new Thread(new StartMineSweeper());
            game.start();
            window.dispose();
        } else {
            //ActionEvent on BoxJButton
            try {
                Coordinate coordinate = Coordinate.parseString(comand);
                if (board.isDigged(coordinate)) {
                    board.digNearby(coordinate);
                } else {
                    board.dig(coordinate);
                }
                window.setVisibility(board.getChanges());
                board.checkWin();
            } catch (GameOver over) {
                window.setVisibility(board.getChanges());
                gameOver(over);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            //Code used for flags
            BoxJButton button = (BoxJButton) e.getComponent();
            if (!button.isDigged()) {
                MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.FLAG_SOUND));
                if (button.getIcon() != null) {
                    button.setIcon(null);
                    button.setFlagged(false);
                    board.removeFlag(button.getPosition());
                } else {
                    button.setIcon(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.FLAGICON)));
                    button.setFlagged(true);
                    board.addFlag(button.getPosition());
                }
                window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber() + "</b></font></html>");
            }
            //Check if the game has ended
            try {
                board.checkWin();
            } catch (GameOver over) {
                gameOver(over);
            }
        }
    }

    private void gameOver(GameOver over) {
        //Execute this block of code when a GameOver exception launches
        int time = window.stopClock();
        String[] options = {MineSweeperLanguageManager.getResourceBundle().getString("Yes"), MineSweeperLanguageManager.getResourceBundle().getString("No")};

        if (over.getGameOverCode() == GameOver.GAMEWON) {
            //If the game is won
            MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.WIN_SOUND));
            MineSweeperScoreboard.addScore(board.getNumColum() + "x" + board.getNumRow(), time);
            int i = JOptionPane.showOptionDialog(
                    (JFrame) window, MineSweeperLanguageManager.getResourceBundle().getString("Play_again"), MineSweeperLanguageManager.getResourceBundle().getString("Win_message"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.CONFFETI)),
                    options, null);
            MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MENU_START_SOUND));
            if (i == JOptionPane.YES_OPTION) {
                Thread game = new Thread(new StartMineSweeper());
                game.start();
                window.dispose();
            } else {
                System.exit(0);
            }
        } else if (over.getGameOverCode() == GameOver.MINEFOUND) {
            //If the player hits a mine
            MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.LOOSE_SOUND));
            int i = JOptionPane.showOptionDialog(
                    (JFrame) window, MineSweeperLanguageManager.getResourceBundle().getString("Play_again"), MineSweeperLanguageManager.getResourceBundle().getString("Mine_message"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.EXPLOSION)),
                    options, null);
            MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.MENU_START_SOUND));
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
