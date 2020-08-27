package MineSweeperGUI.Others;

import MineSweeperResources.MineSweeperLanguageManager;
import MineSweeperResources.MineSweeperPlatformManager;
import MineSweeperResources.MineSweeperResourceManager;

import javax.swing.*;

public class HelpJMenu extends JMenu {
    private final JMenuItem help;
    /*
    Help menu on the minesweeper
     */
    private JMenuItem about;

    public HelpJMenu() {
        super(MineSweeperLanguageManager.getResourceBundle().getString("Help"));
        help = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("How_play"));
        add(help);
        help.addActionListener(e -> new HtmlViewer(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.HELP_PAGE), MineSweeperLanguageManager.getResourceBundle().getString("Help")));

        //Adds the about menu on the help
        if (!MineSweeperPlatformManager.isHostOSMac()) {
            about = new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("About"));
            about.addActionListener(e -> new HtmlViewer(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.ABOUT_PAGE), MineSweeperLanguageManager.getResourceBundle().getString("About")));
            add(about);
        }
    }
}
