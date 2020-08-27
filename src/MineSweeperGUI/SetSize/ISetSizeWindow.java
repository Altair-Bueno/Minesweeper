package MineSweeperGUI.SetSize;

import java.awt.event.ActionListener;

public interface ISetSizeWindow {
    int getxSize();

    int getySize();

    void dispose();

    void setListener(ActionListener listener);

    void updateComponentTree();

    void setMenuSoundToggle(boolean state);
}
