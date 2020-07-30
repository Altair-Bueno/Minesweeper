package MineSweeperGUI.GameGUI;

import MineSweeperGUI.ThemeManager;
import MineSweeperLogic.Coordenada;
import MineSweeperLogic.GameOver;
import MineSweeperLogic.MineSweeperBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControlador implements ActionListener, MouseListener {

    private IGameWindow window;
    private MineSweeperBoard board;

    public GameControlador(IGameWindow window, MineSweeperBoard board) {
        this.window = window;
        this.board = board;
        window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber()+"</b></font></html>");
        board.checkWin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comand=e.getActionCommand();
        if (comand.toLowerCase().contains(ThemeManager.THEME_MANAGER_PACKAGE_NAME)){
            ThemeManager.setTheme(comand);
            window.updateJFrameTheme();
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
                if (over.getGameOverCode() == GameOver.GAMEWON) {
                    //TODO ganado
                    JOptionPane.showMessageDialog((JFrame) window, over.getMessage());
                } else if (over.getGameOverCode() == GameOver.MINEFOUND) {
                    window.setVisibility(board.getVisibility(), board.getTablero());
                    JOptionPane.showMessageDialog((JFrame) window, over.getMessage());
                }
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
                    button.setIcon(new ImageIcon("res/flag.png"));
                    button.setFlagged(true);
                    board.removeFlag();
                }
                window.setStatusPanel("<html><font size=6><b>" + board.getFlagNumber()+"</b></font></html>");
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
