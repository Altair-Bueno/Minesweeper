package MineSweeperGUI.GameGUI;

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
        window.setStatusPanel("Banderas:" + board.getFlagNumber());
        board.checkWin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int column = Integer.parseInt(e.getActionCommand());
            int row = 0;
            while (column >= board.getNumColum()) {
                column = column - board.getNumColum();
                row++;
            }

            Coordenada coordenada = new Coordenada(row, column);
            board.dig(coordenada);
            window.setVisibility(board.getVisibility(), board.getTablero());
            board.checkWin();
        }catch (GameOver over){
            if (over.getGameOverCode()==GameOver.GAMEWON){
                //TODO ganado
                JOptionPane.showMessageDialog((JFrame)window,over.getMessage());
            } else if(over.getGameOverCode()==GameOver.MINEFOUND) {
                JOptionPane.showMessageDialog((JFrame)window,over.getMessage());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3) {
            JButton button= (JButton) e.getComponent();
            if (button.getIcon()!=null) {
                button.setIcon(null);
                button.setEnabled(true);
                board.addFlag();
            } else {
                button.setIcon(new ImageIcon("res/flag.png"));
                button.setEnabled(false);
                board.removeFlag();
            }
            window.setStatusPanel("Banderas" + board.getFlagNumber());
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
