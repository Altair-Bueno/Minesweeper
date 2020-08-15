import MineSweeperGUI.Others.HtmlViewer;
import MineSweeperJavaResources.*;
import com.apple.eawt.Application;


public class MineSweeperMain {
    public static void main(String[] args) {
        ThemeManager.setTheme(ThemeManager.getDefinedTheme());

        //Codigo para apps ejecutandose en macOS
        if (MineSweeperPlatformManager.isHostOSMac()) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", MineSweeperResourceManager.getAPPNAME());
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            Application.getApplication().setDockIconImage(MineSweeperResourceManager.getAppIcon().getImage());
            Application.getApplication().setAboutHandler(e -> new HtmlViewer(MineSweeperResourceManager.getAboutPage(),MineSweeperLanguageManager.getResourceBundle().getString("About")));
        }
        // Test code for colors
/*
        ThemeManager.setTheme(ThemeManager.MORADO);
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int i=0;
        for (String color:ThemeManager.getFontColors()){
            if (color!=null) {
                JButton temp= new JButton(
                        "<html><b><font size=6 color="+color+">"+i+"</font></b></html>");
                temp.setBackground(ThemeManager.getDiggedBackground());
                jPanel.add(temp);
                i++;
            }
        }
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
*/

        Thread game = new Thread(new StartMineSweeper());
        game.start();
    }
}
