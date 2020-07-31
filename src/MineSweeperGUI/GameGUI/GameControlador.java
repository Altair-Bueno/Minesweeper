package MineSweeperGUI.GameGUI;

import MineSweeperJavaResources.MineSweeperResourceManager;
import MineSweeperJavaResources.ThemeManager;
import MineSweeperLogic.Coordenada;
import MineSweeperLogic.GameOver;
import MineSweeperLogic.MineSweeperBoard;
import MineSweeperJavaResources.StartMineSweeper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControlador implements ActionListener, MouseListener {

    public static final String NEW="NEW";

    private IGameWindow window;
    private MineSweeperBoard board;

    public GameControlador(IGameWindow window, MineSweeperBoard board) {
        this.window = window;
        this.board = board;
        window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber()+"</b></font></html>");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comand=e.getActionCommand();
        if (comand.toLowerCase().contains(ThemeManager.THEME_MANAGER_PACKAGE_NAME)){
            ThemeManager.setTheme(comand);
            window.updateJFrameTheme();
        }else if(comand.equals(NEW)){
            Thread game =new Thread(new StartMineSweeper());
            game.start();
            window.dispose();
        }else {
            try {
                int column = Integer.parseInt(comand);
                int row = 0;

                while (column >= board.getNumColum()) {
                    column = column - board.getNumColum();
                    row++;
                }

                Coordenada coordenada = new Coordenada(row, column);
                board.dig(coordenada);
                window.setVisibility(board.getVisibility(), board.getTablero());
                board.checkWin();
            }catch (GameOver over) {
                gameOver(over);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3) {
            BoxJButton button= (BoxJButton) e.getComponent();
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
                window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber()+"</b></font></html>");
            }
            try {
                board.checkWin();
            }catch (GameOver over){
                gameOver(over);
            }
        }
    }

    private void gameOver(GameOver over){
        window.stopClock();
        if (over.getGameOverCode() == GameOver.GAMEWON) {
            //TODO ganado y play again
            int i=JOptionPane.showConfirmDialog((JFrame) window,over.getMessage(),"Play again?",JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                Thread game = new Thread(new StartMineSweeper());
                game.start();
                window.dispose();
            } else {
                System.exit(0);
            }
        } else if (over.getGameOverCode() == GameOver.MINEFOUND) {
            int i=JOptionPane.showConfirmDialog((JFrame) window,over.getMessage(),"Play again?",JOptionPane.YES_NO_OPTION);
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
