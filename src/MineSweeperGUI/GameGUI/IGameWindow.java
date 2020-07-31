package MineSweeperGUI.GameGUI;

public interface IGameWindow {
    void setControlador(GameControlador controlador);
    void setStatusPanel(String status);
    void setVisibility(boolean [][] visibility,int [][] values);
    void updateJFrameTheme();
    void stopClock();
    int getPuntuation();
    void dispose();
}
