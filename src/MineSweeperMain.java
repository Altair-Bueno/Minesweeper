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

        Thread game = new Thread(new StartMineSweeper());
        game.start();
    }
}
