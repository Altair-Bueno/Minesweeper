package MineSweeperResources;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    public static final String THEME_MANAGER_PACKAGE_NAME = "flatlaf";

    //Temas disponibles
    public static final String CLARO = "com.formdev.flatlaf.FlatLightLaf";
    private static final String[] CLARO_COLORS = {"#000000", "#0000FF", "#3f9957", "#FF0000", "#f000ec", "#004A2F", "#916C80", "#eb9e10", "#ed0cc4", "#30BCED"};
    private static final Color CLARO_BACKGROUND_UNDIGGED = Color.GRAY;
    private static final Color CLARO_BACKGROUND_DIGGED = Color.WHITE;

    public static final String DRACULA = "com.formdev.flatlaf.FlatDarculaLaf";
    private static final String[] DRACULA_COLORS = {"#FFFFFF", "#7876e3", "#c90c0c", "#e622cb", "#8c417d", "#004A2F", "#d49c61", "#bd6302", "#00f700", "#8f9c8f"};
    private static final Color DRACULA_BACKGROUND_UNDIGGED = new Color(0x7D7D7D);
    private static final Color DRACULA_BACKGROUND_DIGGED = new Color(0xC5C5C5);

    public static final String MORADO = "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme";
    private static final String[] MORADO_COLORS = {"#000000", "#0000FF", "#3f9957", "#FF0000", "#801969", "#093827", "#916C80", "#eb9e10", "#664601", "#30BCED"};
    private static final Color MORADO_BACKGROUND_UNDIGGED = new Color(0xB47EB3);
    private static final Color MORADO_BACKGROUND_DIGGED = new Color(0xFFD5FF);

    private static String temaActual = CLARO;
    private static String[] fontColors = CLARO_COLORS;
    private static Color undiggedBackground = CLARO_BACKGROUND_UNDIGGED;
    private static Color diggedBackground = CLARO_BACKGROUND_DIGGED;

    public static String getDefinedTheme() {
        return temaActual;
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
        return CLARO;
    }

    public static void setTheme(String s) {
        try {
            switch (s) {
                case CLARO:
                    temaActual = CLARO;
                    fontColors = CLARO_COLORS;
                    undiggedBackground = CLARO_BACKGROUND_UNDIGGED;
                    diggedBackground = CLARO_BACKGROUND_DIGGED;
                    break;
                case DRACULA:
                    temaActual = DRACULA;
                    fontColors = DRACULA_COLORS;
                    undiggedBackground = DRACULA_BACKGROUND_UNDIGGED;
                    diggedBackground = DRACULA_BACKGROUND_DIGGED;
                    break;
                case MORADO:
                    temaActual = MORADO;
                    fontColors = MORADO_COLORS;
                    undiggedBackground = MORADO_BACKGROUND_UNDIGGED;
                    diggedBackground = MORADO_BACKGROUND_DIGGED;
                    break;
            }
            UIManager.setLookAndFeel(temaActual);
        } catch (Exception e) {
            System.err.println("Theme " + temaActual + " not found");
        }
    }
}
