import MineSweeperGUI.ThemeManager;
import MineSweeperLogic.MineSweeperBoard;

import javax.imageio.ImageIO;
import javax.swing.*;

import MineSweeperLogic.StartMineSweeper;
import com.apple.eawt.*;

public class MineSweeper {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //Codigo para apps ejecutandose en macOS
        ThemeManager.setTheme(ThemeManager.getDefinedTheme());

        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", MineSweeperBoard.APPNAME);
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            try {
                Application.getApplication().setDockIconImage(new ImageIcon(ImageIO.read(MineSweeper.class.getResourceAsStream("/"+MineSweeperBoard.ICON))).getImage());
            } catch (Exception e){
                Application.getApplication().setDockIconImage(new ImageIcon(MineSweeperBoard.ICON).getImage());
            }
        }
        /* Test code for colors
        ThemeManager.setTheme(ThemeManager.MORADO);
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (String color:ThemeManager.getFontColors()){
            if (color!=null) {
                JButton temp= new JButton(
                        "<html><b><font size=4 color="+color+">hola</font></b></html>");
                temp.setBackground(ThemeManager.getDiggedBackground());
                jPanel.add(temp);
            }
        }
        jFrame.pack();
        jFrame.setVisible(true);

         */
        Thread game =new Thread(new StartMineSweeper());
        game.start();
    }
}
