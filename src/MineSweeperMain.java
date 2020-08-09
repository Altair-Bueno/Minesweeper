import MineSweeperJavaResources.*;
import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MineSweeperMain {
    public static void main(String[] args) {
        ThemeManager.setTheme(ThemeManager.getDefinedTheme());

        //Codigo para apps ejecutandose en macOS
        if (MineSweeperPlatformManager.isHostOSMac()) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", MineSweeperResourceManager.getAPPNAME());
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            Application.getApplication().setDockIconImage(MineSweeperResourceManager.getAppIcon().getImage());
            Application.getApplication().setAboutHandler(e -> {
                JEditorPane jEditorPane;
                try {
                    jEditorPane = new JEditorPane(MineSweeperResourceManager.getAboutPage());
                } catch (IOException ioException) {
                    jEditorPane = new JEditorPane();
                    jEditorPane.setContentType("text/html");
                    jEditorPane.setText("<html>Page not found.</html>");
                }
                jEditorPane.setEditable(false);
                JScrollPane jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setPreferredSize(new Dimension(500,300));
                JFrame about = new JFrame(MineSweeperLanguageManager.getResourceBundle().getString("About"));
                about.add(jScrollPane);
                about.pack();
                about.setLocationRelativeTo(null);
                about.setVisible(true);
            });
        }
        // Test code for colors
        /*
        ThemeManager.setTheme(ThemeManager.DRACULA);
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
