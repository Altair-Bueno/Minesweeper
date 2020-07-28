package MineSweeperGUI.GameGUI;

import MineSweeperLogic.Coordenada;
import MineSweeperLogic.MineSweeperBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControlador implements ActionListener {

    private IGameWindow window;
    private MineSweeperBoard board;

    public GameControlador(IGameWindow window, MineSweeperBoard board) {
        this.window = window;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int column = Integer.parseInt(e.getActionCommand());
        int row =0;
        while (column >=board.getNumColum()){
            column = column -board.getNumColum();
            row++;
        }

        Coordenada coordenada=new Coordenada(row,column);
        board.dig(coordenada);
        window.setVisibility(board.getVisibility(),board.getTablero());
    }
}
