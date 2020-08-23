package MineSweeperGUI.GameGUI;

import MineSweeperLogic.Coordinate;

import java.util.Map;

public interface IGameWindow {
    void setListener(GameListener listener);

    void setStatusPanel(String status);

    void setVisibility(Map<Coordinate, Integer> changedVisibility);

    void updateJFrameTheme();

    int stopClock();

    void dispose();
}
