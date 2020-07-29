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
    private GameControlador controlador;
    private static final String[] COLORS=
            {"#0000FF","#00FF00","#FF0000","#f000ec","#00f050","#f0ec00","#eb9e10","#ed0cc4","#0cedd6"};

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
            tempButton.setBackground(Color.GRAY);
            gameButtonslist.add(tempButton);
            gameButtons.add(tempButton);
        }

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setControlador(GameControlador controlador) {
        this.controlador=controlador;
        for (JButton button : gameButtonslist) {
            button.addActionListener(controlador);
            button.addMouseListener(controlador);
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
                    //TODO arreglar bugs con el enable/disable
                    button.removeActionListener(controlador);
                    button.setBackground(Color.WHITE);

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
