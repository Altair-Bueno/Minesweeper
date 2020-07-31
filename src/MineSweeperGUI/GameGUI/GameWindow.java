package MineSweeperGUI.GameGUI;

import MineSweeperGUI.SetSize.SelectSizeWindow;
import MineSweeperGUI.ThemeManager;
import MineSweeperGUI.ThemeManagerJMenu;
import MineSweeperLogic.MineSweeperBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static MineSweeperGUI.GameGUI.BoxJButton.FLAGICON;

public class GameWindow extends JFrame implements IGameWindow,Runnable{
    private static final String CLOCKICON="res/time.png";

    private JPanel rootPane;
    private JPanel statusPanel;
    private JLabel flagNumberJLabel;
    private JPanel gameButtons;
    private JLabel time;

    private long startTime;
    private Thread clock;
    private boolean clockIsStopped;

    private List<BoxJButton> gameButtonslist;
    private GameControlador controlador;

    private JMenuBar jMenuBar;
    private ThemeManagerJMenu themeManagerJMenu;

    public GameWindow(int xSize, int ySize){
        add(rootPane);
        try {
            flagNumberJLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("/"+ FLAGICON)));
            time.setIcon(new ImageIcon(ClassLoader.getSystemResource("/"+CLOCKICON)));
        }catch (Exception e){
            flagNumberJLabel.setIcon(new ImageIcon(FLAGICON));
            time.setIcon(new ImageIcon(CLOCKICON));
        }
        //TODO jmenubar. Añadir nuevo
        jMenuBar=new JMenuBar();
        themeManagerJMenu =new ThemeManagerJMenu();
        jMenuBar.add(themeManagerJMenu);
        setJMenuBar(jMenuBar);

        gameButtonslist=new ArrayList<>(16);
        gameButtons.setLayout(new GridLayout(xSize,ySize));

        int resolution = getToolkit().getScreenResolution();

        Dimension dimension=new Dimension((int) Math.round(resolution*0.25),(int) Math.round(resolution*0.25));

        for (int i=0;i<xSize*ySize;i++) {
            BoxJButton tempButton=new BoxJButton();
            tempButton.setActionCommand(i+"");
            tempButton.setMinimumSize(dimension);
            tempButton.setPreferredSize(dimension);
            tempButton.setBackground(ThemeManager.getUndiggedBackground());
            gameButtonslist.add(tempButton);
            gameButtons.add(tempButton);
        }


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(MineSweeperBoard.APPNAME);
        setName(MineSweeperBoard.APPNAME);

        if (!System.getProperty("os.name").contains("Mac")) {
            try {
                setIconImage(ImageIO.read(SelectSizeWindow.class.getResourceAsStream("/" + MineSweeperBoard.ICON)));
            } catch (Exception e) {
                setIconImage(new ImageIcon(MineSweeperBoard.ICON).getImage());
            }
        }

        clockIsStopped=false;
        clock=new Thread(this);
        startTime=0;
        clock.start();

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
        themeManagerJMenu.setActionListener(controlador);
    }
    @Override
    public void setStatusPanel(String status) {
        flagNumberJLabel.setText(status);
    }

    @Override
    public void setVisibility(boolean[][] visibility,int [][] values) {
        Iterator<BoxJButton> iterator= gameButtonslist.iterator();
        for (int i=0; i<visibility.length;i++){
            for (int u=0;u<visibility[0].length;u++) {

                BoxJButton button = iterator.next();

                if (visibility[i][u] && !button.isDigged()&& !button.isFlagged()){
                    button.removeActionListener(controlador);
                    button.setDigged(true);
                    button.setBackground(ThemeManager.getDiggedBackground());

                    if (values[i][u]==MineSweeperBoard.MINA){
                        button.setIcon(new ImageIcon("res/mineButton.png"));
                    } else if (values[i][u]>0) {
                        button.setText("<html><b><font size=5 color="+ ThemeManager.getFontColors()[values[i][u]] +">" +values[i][u]+"</font></b></html>");
                    }
                }
            }
        }
    }

    @Override
    public void updateJFrameTheme() {
        SwingUtilities.updateComponentTreeUI(this);
        for (BoxJButton button: gameButtonslist) {
            Color temp;
            if (button.isDigged()) {
                temp=ThemeManager.getDiggedBackground();
            } else {
                temp=ThemeManager.getUndiggedBackground();
            }
            button.setBackground(temp);
        }
    }

    @Override
    public void stopClock() {
        clockIsStopped=true;
    }

    @Override
    public String  getPuntuation() {
        return time.getText();
    }


    @Override
    public void run() {
        while (!clockIsStopped){
            time.setText("<html><b><font size=5>"+startTime+
            "</b></font></html>");
            startTime++;
            try {
                Thread.sleep(1000);
            }catch (Exception ignored){}
        }
    }
}
