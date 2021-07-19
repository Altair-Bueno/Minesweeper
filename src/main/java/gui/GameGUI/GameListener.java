package gui.GameGUI;

import board.Coordinate;
import board.GameOver;
import board.MineSweeperBoard;
import manager.*;

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
    public static final String TOGGLESOUND = "SOUND";

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
        if (comand.toLowerCase().contains(Theme.THEME_MANAGER_PACKAGE_NAME)) {
            //Theme management code
            Theme.setTheme(comand);
            window.updateJFrameTheme();
        } else if (comand.equals(NEW)) {
            //Terminates this thread and creates another game thread
            Thread game = new Thread(new StartMineSweeper());
            game.start();
            window.dispose();
        } else if (comand.equals(TOGGLESOUND)) {
            Jukebox.toggleSoud();
            window.setMenuSoundToggle(Jukebox.canPlayMusic());
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
                Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.FLAG_SOUND));
                if (button.getIcon() != null) {
                    button.setIcon(null);
                    button.setFlagged(false);
                    board.removeFlag(button.getPosition());
                } else {
                    button.setIcon(new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.FLAGICON)));
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
        String[] options = {Language.getResourceBundle().getString("Yes"), Language.getResourceBundle().getString("No")};

        if (over.getGameOverCode() == GameOver.GAMEWON) {
            //If the game is won
            Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.WIN_SOUND));
            MineSweeperScoreboard.addScore(board.getNumColum() + "x" + board.getNumRow(), time);
            int i = JOptionPane.showOptionDialog(
                    (JFrame) window, Language.getResourceBundle().getString("Play_again"), Language.getResourceBundle().getString("Win_message"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.CONFFETI)),
                    options, null);
            Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.MENU_START_SOUND));
            if (i == JOptionPane.YES_OPTION) {
                Thread game = new Thread(new StartMineSweeper());
                game.start();
                window.dispose();
            } else {
                System.exit(0);
            }
        } else if (over.getGameOverCode() == GameOver.MINEFOUND) {
            //If the player hits a mine
            Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.LOOSE_SOUND));
            int i = JOptionPane.showOptionDialog(
                    (JFrame) window, Language.getResourceBundle().getString("Play_again"), Language.getResourceBundle().getString("Mine_message"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(ResourceManager.getResourceURL(ResourceManager.Icon.EXPLOSION)),
                    options, null);
            Jukebox.play(ResourceManager.getResourceURL(ResourceManager.SoundFiles.MENU_START_SOUND));
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
