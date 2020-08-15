package MineSweeperGUI.Others;

import MineSweeperJavaResources.MineSweeperLanguageManager;
import MineSweeperJavaResources.MineSweeperResourceManager;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class HtmlViewer {
    public HtmlViewer(URL url,String title){
        JEditorPane jEditorPane;
        try {
            jEditorPane = new JEditorPane(url);
        } catch (IOException ioException) {
            jEditorPane = new JEditorPane();
            jEditorPane.setContentType("text/html");
            jEditorPane.setText("<html>Page not found.</html>");
        }
        jEditorPane.setPreferredSize(new Dimension(500,300));
        jEditorPane.setEditable(false);
        jEditorPane.addHyperlinkListener((a)->{
            if (HyperlinkEvent.EventType.ACTIVATED.equals(a.getEventType())) {
                System.out.println(a.getURL());
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(a.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JScrollPane jScrollPane = new JScrollPane(jEditorPane);
        //jScrollPane.setPreferredSize());
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JFrame about = new JFrame(title);
        about.add(jScrollPane);
        about.pack();
        about.setLocationRelativeTo(null);
        about.setVisible(true);
    }
}
