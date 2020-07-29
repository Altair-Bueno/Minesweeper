import MineSweeperGUI.ThemeManager;
import MineSweeperLogic.MineSweeperBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.apple.eawt.*;

public class MineSweeper {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //Codigo para apps ejecutandose en macOS
        ThemeManager.changeTheme(ThemeManager.getDefinedTheme());

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
        MineSweeperGame mineSweeperGame = new MineSweeperGame();
    }
}
