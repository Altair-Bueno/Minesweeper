package manager;

import java.util.ResourceBundle;

public class Language {

    private static final String RES_BUNDLE_LOCATION = "properties/minesweeper";
    private static final ResourceBundle activeResourceBundle =
            ResourceBundle
                    .getBundle(RES_BUNDLE_LOCATION);

    public static ResourceBundle getResourceBundle() {
        return activeResourceBundle;
    }
}
