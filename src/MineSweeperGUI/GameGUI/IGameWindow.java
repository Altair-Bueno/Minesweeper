package MineSweeperGUI.GameGUI;

public interface IGameWindow {
    void setControlador(GameControlador controlador);
    void setNumMines(int numMines);
    void setStatusPanel(String status);
    void setVisibility(boolean [][] visibility,int [][] values);
    void updateJFrameTheme();
}
