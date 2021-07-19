import gui.extra.HtmlViewer;
import manager.*;
import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.QuitStrategy;


public class MineSweeperMain {
    public static void main(String[] args) {
        Theme.setTheme(Theme.getDefinedTheme());

        //macOS platform code
        if (Platform.isHostOSMac()) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", Loader.getAPPNAME());
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.textantialiasing", "true");
            Desktop.getDesktop().setQuitStrategy(QuitStrategy.NORMAL_EXIT);
            Desktop.getDesktop().setAboutHandler(e ->
                    new HtmlViewer(Loader.getResourceURL(Loader.HTMLDoc.ABOUT_PAGE), Language.getResourceBundle().getString("About")));
            Application.getApplication().setDockIconImage(
                    new ImageIcon(Loader.getResourceURL(Loader.Icon.APPICON)).getImage());
        }

        //Starts the game on a new thread
        Thread game = new Thread(new StartMineSweeper());
        game.start();
    }
}
