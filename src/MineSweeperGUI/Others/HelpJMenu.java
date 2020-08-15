package MineSweeperGUI.Others;

import MineSweeperJavaResources.MineSweeperLanguageManager;
import MineSweeperJavaResources.MineSweeperPlatformManager;
import MineSweeperJavaResources.MineSweeperResourceManager;
import com.apple.eawt.Application;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;

public class HelpJMenu extends JMenu {
    private JMenuItem about;
    private final JMenuItem help;

    public HelpJMenu(){
        super(MineSweeperLanguageManager.getResourceBundle().getString("Help"));
        help=new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("How_play"));
        add(help);
        help.addActionListener(e-> new HtmlViewer(MineSweeperResourceManager.getHelpPage(),MineSweeperLanguageManager.getResourceBundle().getString("Help")));
        if (!MineSweeperPlatformManager.isHostOSMac()){
            about=new JMenuItem(MineSweeperLanguageManager.getResourceBundle().getString("About"));
            about.addActionListener(e->new HtmlViewer(MineSweeperResourceManager.getAboutPage(),MineSweeperLanguageManager.getResourceBundle().getString("About")));
            add(about);
        }
    }
}
