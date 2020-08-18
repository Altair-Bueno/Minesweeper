import MineSweeperGUI.Others.HtmlViewer;
import MineSweeperResources.*;
import com.apple.eawt.Application;

import javax.swing.*;


public class MineSweeperMain {
    public static void main(String[] args) {
        ThemeManager.setTheme(ThemeManager.getDefinedTheme());

        //Codigo para apps ejecutandose en macOS
        if (MineSweeperPlatformManager.isHostOSMac()) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", MineSweeperResourceManager.getAPPNAME());
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.textantialiasing", "true");
            Application.getApplication().setDockIconImage(
                    new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.APPICON)).getImage());
            Application.getApplication().setAboutHandler(e ->
                    new HtmlViewer(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.ABOUT_PAGE), MineSweeperLanguageManager.getResourceBundle().getString("About")));
        }

        Thread game = new Thread(new StartMineSweeper());
        game.start();
    }
}
