package MineSweeperJavaResources;

import java.util.ResourceBundle;

public class MineSweeperLanguageManager {

    private static final String RES_BUNDLE_LOCATION="MineSweeperJavaResources.Properties.MineSweeper";
    private static ResourceBundle activeResourceBundle;

    public static ResourceBundle getResourceBundle(){
        if (activeResourceBundle==null) activeResourceBundle=ResourceBundle.getBundle(RES_BUNDLE_LOCATION);
        return activeResourceBundle;
    }
}
