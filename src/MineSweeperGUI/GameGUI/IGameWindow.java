package MineSweeperGUI.GameGUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public interface IGameWindow {
    void setControlador(ActionListener actionListener, MouseListener mouseListener);
    void setNumMines(int numMines);
    void setStatusPanel(String status);
    void setVisibility(boolean [][] visibility,int [][] values);
}
