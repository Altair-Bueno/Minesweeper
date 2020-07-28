package MineSweeperGUI.GameGUI;

import MineSweeperLogic.MineSweeperBoard;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FlagControlador implements MouseListener {

    private MineSweeperBoard board;

    public FlagControlador(MineSweeperBoard board) {
        this.board=board;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3) {
            JButton button= (JButton) e.getComponent();
            if (button.getIcon()!=null) {
                button.setIcon(null);
                button.setEnabled(true);
                board.removeFlag();
            } else {
                button.setIcon(new ImageIcon("res/flag.png"));
                button.setEnabled(false);
                board.addFlag();
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
