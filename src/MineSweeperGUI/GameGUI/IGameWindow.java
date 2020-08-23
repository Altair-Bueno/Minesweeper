package MineSweeperGUI.GameGUI;

import MineSweeperLogic.Coordenada;

import java.util.Map;

public interface IGameWindow {
    void setControlador(GameControlador controlador);

    void setStatusPanel(String status);

    void setVisibility(Map<Coordenada, Integer> changedVisibility);

    void updateJFrameTheme();

    int stopClock();

    void dispose();
}
