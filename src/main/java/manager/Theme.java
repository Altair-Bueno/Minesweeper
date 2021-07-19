package manager;

import javax.swing.*;
import java.awt.*;

public class Theme {
    public static final String THEME_MANAGER_PACKAGE_NAME = "flatlaf";

    //Availables themes

    //Light theme
    public static final String LIGHT = "com.formdev.flatlaf.FlatLightLaf";
    //Dracula theme
    public static final String DRACULA = "com.formdev.flatlaf.FlatDarculaLaf";
    //Purple theme
    public static final String PURPLE = "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme";
    private static final String[] LIGHT_COLORS = {"#000000", "#0000FF", "#3f9957", "#FF0000", "#f000ec", "#004A2F", "#916C80", "#eb9e10", "#ed0cc4", "#30BCED"};
    private static final Color LIGHT_BACKGROUND_UNDIGGED = Color.GRAY;
    private static final Color LIGHT_BACKGROUND_DIGGED = Color.WHITE;
    private static final String[] DRACULA_COLORS = {"#FFFFFF", "#7876e3", "#c90c0c", "#e622cb", "#8c417d", "#004A2F", "#d49c61", "#bd6302", "#00f700", "#8f9c8f"};
    private static final Color DRACULA_BACKGROUND_UNDIGGED = new Color(0x7D7D7D);
    private static final Color DRACULA_BACKGROUND_DIGGED = new Color(0xC5C5C5);
    private static final String[] PURPLE_COLORS = {"#000000", "#0000FF", "#3f9957", "#FF0000", "#801969", "#093827", "#916C80", "#eb9e10", "#664601", "#30BCED"};
    private static final Color PURPLE_BACKGROUND_UNDIGGED = new Color(0xB47EB3);
    private static final Color PURPLE_BACKGROUND_DIGGED = new Color(0xFFD5FF);

    private static String actualTheme = LIGHT;
    private static String[] fontColors = LIGHT_COLORS;
    private static Color undiggedBackground = LIGHT_BACKGROUND_UNDIGGED;
    private static Color diggedBackground = LIGHT_BACKGROUND_DIGGED;

    public static String getDefinedTheme() {
        return actualTheme;
    }

    public static String[] getFontColors() {
        return fontColors;
    }

    public static Color getUndiggedBackground() {
        return undiggedBackground;
    }

    public static Color getDiggedBackground() {
        return diggedBackground;
    }

    public static String getDefaultTheme() {
        return LIGHT;
    }

    public static void setTheme(String s) {
        try {
            switch (s) {
                case LIGHT:
                    actualTheme = LIGHT;
                    fontColors = LIGHT_COLORS;
                    undiggedBackground = LIGHT_BACKGROUND_UNDIGGED;
                    diggedBackground = LIGHT_BACKGROUND_DIGGED;
                    break;
                case DRACULA:
                    actualTheme = DRACULA;
                    fontColors = DRACULA_COLORS;
                    undiggedBackground = DRACULA_BACKGROUND_UNDIGGED;
                    diggedBackground = DRACULA_BACKGROUND_DIGGED;
                    break;
                case PURPLE:
                    actualTheme = PURPLE;
                    fontColors = PURPLE_COLORS;
                    undiggedBackground = PURPLE_BACKGROUND_UNDIGGED;
                    diggedBackground = PURPLE_BACKGROUND_DIGGED;
                    break;
            }
            UIManager.setLookAndFeel(actualTheme);
        } catch (Exception e) {
            System.err.println("Theme " + actualTheme + " not found");
        }
    }
}
