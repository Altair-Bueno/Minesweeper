package MineSweeperGUI.SetSize;

import java.awt.event.ActionListener;

public interface ISetSizeWindow {
    int getxSize();
    int getySize();
    void dispose();
    void setControlador(ActionListener actionListener);
}
