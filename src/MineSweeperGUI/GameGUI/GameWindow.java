package MineSweeperGUI.GameGUI;

import MineSweeperLogic.MineSweeperBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWindow extends JFrame implements IGameWindow{
    private JPanel rootPane;
    private JPanel statusPanel;
    private JLabel mineNumberJLabel;
    private JLabel flagNumberJLabel;
    private JPanel gameButtons;

    private List<JButton> gameButtonslist;
    private static final int[] COLORS=
            {0x0000FF,0x00FF00,0xFF0000,0xf000ec,0x00f050,0xf0ec00,0xeb9e10,0xed0cc4,0x0cedd6};

    public GameWindow(int xSize, int ySize){
        add(rootPane);

        gameButtonslist=new ArrayList<>(16);
        gameButtons.setLayout(new GridLayout(xSize,ySize));

        int resolution = getToolkit().getScreenResolution();

        Dimension dimension=new Dimension((int) Math.round(resolution*0.25),(int) Math.round(resolution*0.25));

        for (int i=0;i<xSize*ySize;i++) {
            JButton tempButton=new JButton();
            tempButton.setActionCommand(i+"");
            tempButton.setMinimumSize(dimension);
            tempButton.setPreferredSize(dimension);
            gameButtonslist.add(tempButton);
            gameButtons.add(tempButton);
        }

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setControlador(ActionListener controlador, MouseListener mouseListener) {
        for (JButton button : gameButtonslist) {
            button.addActionListener(controlador);
            button.addMouseListener(mouseListener);
        }
    }

    @Override
    public void setNumMines(int numMines) {
        mineNumberJLabel.setText("Numero de minas" + numMines);
    }

    @Override
    public void setStatusPanel(String status) {
        flagNumberJLabel.setText(status);
    }

    @Override
    public void setVisibility(boolean[][] visibility,int [][] values) {
        Iterator<JButton> iterator= gameButtonslist.iterator();
        for (int i=0; i<visibility.length;i++){
            for (int u=0;u<visibility[0].length;u++) {

                JButton button = iterator.next();

                if (visibility[i][u] && button.isEnabled()){
                    button.setEnabled(false);

                    if (values[i][u]==MineSweeperBoard.MINA){
                        button.setIcon(new ImageIcon("res/mineButton.png"));
                    } else if (values[i][u]>0) {
                        button.setText("<html><font color="+COLORS[values[i][u]] +">" +values[i][u]+"</font> </html>");
                    }

                }
            }
        }
    }

}
