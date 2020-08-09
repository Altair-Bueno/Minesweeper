package MineSweeperGUI.GameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoxJButton extends JButton {
    private boolean flagged;
    private boolean digged;
    private Integer value; //Null es desconocido

    public BoxJButton() {
        flagged = false;
        digged = false;
        value=null;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setDigged(boolean digged) {
        this.digged = digged;
    }

    public boolean isDigged() {
        return digged;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        if (!flagged) {
            super.fireActionPerformed(event);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
