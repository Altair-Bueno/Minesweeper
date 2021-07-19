package gui.GameGUI;

import board.Coordinate;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoxJButton extends JButton {
    /*
    A special JButton for MineSweeper
     */
    private boolean flagged;
    private boolean digged;
    private Coordinate coordinate;
    private Integer value; //Null es desconocido

    public BoxJButton() {
        flagged = false;
        digged = false;
        value = null;
        coordinate = null;
    }

    public BoxJButton(Coordinate coordinate) {
        this();
        this.coordinate = coordinate;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isDigged() {
        return digged;
    }

    public void setDigged(boolean digged) {
        this.digged = digged;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        if (!flagged) {
            super.fireActionPerformed(event);
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Coordinate getPosition() {
        if (coordinate == null) throw new RuntimeException("No value saved");
        return coordinate;
    }
}
