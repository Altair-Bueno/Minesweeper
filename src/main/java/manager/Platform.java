package manager;

import gui.extra.HtmlViewer;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Platform {
    private static final String hostOS = System.getProperty("os.name").toLowerCase();

    private static final String MACOS_X = "mac";
    private static final String MSWINDOWS = "windows";

    public static int getMainKeyboardActionEvent() {
        return hostOS.contains(MACOS_X) ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK;
    }
    public static void setup() {
        //macOS platform code
        if (Platform.isHostOSMac()) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", Loader.getAPPNAME());
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.textantialiasing", "true");
            System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");
            Desktop.getDesktop().setAboutHandler(e ->
                    new HtmlViewer(Loader.getResourceURL(Loader.HTMLDoc.ABOUT_PAGE),
                            Language.getResourceBundle().getString("About")));
        }
    }

    public static boolean isHostOSMac() {
        return hostOS.contains(MACOS_X);
    }

    public static boolean isHostOSWindows() {
        return hostOS.contains(MSWINDOWS);
    }

    public static String getHostOS() {
        return hostOS;
    }

}
