package MineSweeperJavaResources;

import java.util.Locale;
import java.util.ResourceBundle;

public class MineSweeperLanguageManager {

    private static final String RES_BUNDLE_LOCATION="MineSweeperJavaResources.Properties.MineSweeper";
    private static ResourceBundle activeResourceBundle;

    public static ResourceBundle getResourceBundle(){
        if (activeResourceBundle==null) activeResourceBundle=ResourceBundle.getBundle(RES_BUNDLE_LOCATION);
        return activeResourceBundle;
    }
    //TODO change language
    public static void changeLanguageTo(String s){
        try {
            Locale.setDefault(new Locale(s));
            activeResourceBundle=ResourceBundle.getBundle(RES_BUNDLE_LOCATION,new Locale(s));
        } catch (Exception e) {
            System.err.println("Language not supported");
        }
    }
}
