package gui.extra;

import manager.Language;
import manager.Platform;
import manager.Loader;

import javax.swing.*;

public class HelpJMenu extends JMenu {
    private final JMenuItem help;
    /*
    Help menu on the minesweeper
     */
    private JMenuItem about;

    public HelpJMenu() {
        super(Language.getResourceBundle().getString("Help"));
        help = new JMenuItem(Language.getResourceBundle().getString("How_play"));
        add(help);
        help.addActionListener(e -> new HtmlViewer(Loader.getResourceURL(Loader.HTMLDoc.HELP_PAGE), Language.getResourceBundle().getString("Help")));

        //Adds the about menu on the help
        if (!Platform.isHostOSMac()) {
            about = new JMenuItem(Language.getResourceBundle().getString("About"));
            about.addActionListener(e -> new HtmlViewer(Loader.getResourceURL(Loader.HTMLDoc.ABOUT_PAGE), Language.getResourceBundle().getString("About")));
            add(about);
        }
    }
}
