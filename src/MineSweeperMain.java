import MineSweeperJavaResources.*;

import com.apple.eawt.*;

public class MineSweeperMain {
    public static void main(String[] args){
        //Codigo para apps ejecutandose en macOS
        ThemeManager.setTheme(ThemeManager.getDefinedTheme());

        if(MineSweeperPlatformManager.isHostOSMac()){
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", MineSweeperResourceManager.getAPPNAME());
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            Application.getApplication().setDockIconImage(MineSweeperResourceManager.getAppIcon().getImage());
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
