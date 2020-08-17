package MineSweeperResources;

import java.util.ResourceBundle;

public class MineSweeperLanguageManager {

    private static final String RES_BUNDLE_LOCATION = "MineSweeperResources.Properties.MineSweeper";
    private static final ResourceBundle activeResourceBundle = ResourceBundle.getBundle(RES_BUNDLE_LOCATION);

    public static ResourceBundle getResourceBundle() {
        return activeResourceBundle;
    }
}
