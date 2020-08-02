package MineSweeperJavaResources;

import java.awt.event.ActionEvent;

public class MineSweeperPlatformManager {
    private static String hostOS=System.getProperty("os.name").toLowerCase();

    private static final String MACOS_X="mac";
    private static final String MSWINDOWS="windows";

    public static int getMainKeyboardActionEvent(){
        return hostOS.contains(MACOS_X)? ActionEvent.META_MASK : ActionEvent.CTRL_MASK;
    }
    public static boolean isHostOSMac(){
        return hostOS.contains(MACOS_X);
    }
    public static boolean isHostOSWindows(){
        return hostOS.contains(MSWINDOWS);
    }
    public static String  getHostOS(){
        return hostOS;
    }

}
