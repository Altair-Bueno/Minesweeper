package MineSweeperGUI;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    //Temas disponibles

    public static final String THEME_MANAGER_PACKAGE_NAME ="flatlaf";

    public static final String CLARO="com.formdev.flatlaf.FlatLightLaf";
    private static final String[] CLARO_COLORS={"#0000FF","#00FF00","#FF0000","#f000ec","#00f050","#f0ec00","#eb9e10","#ed0cc4","#0cedd6"};
    private static final Color CLARO_BACKGROUND_UNDIGGED=Color.GRAY;
    private static final Color CLARO_BACKGROUND_DIGGED=Color.WHITE;

    //TODO colores
    public static final String DRACULA="com.formdev.flatlaf.FlatDarculaLaf";
    private static final String[] DRACULA_COLORS = CLARO_COLORS;
    private static final Color DRACULA_BACKGROUND_UNDIGGED = Color.blue;
    private static final Color DRACULA_BACKGROUND_DIGGED = Color.red;

    public static final String MORADO="com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme";
    private static final String[] MORADO_COLORS = null;
    private static final Color MORADO_BACKGROUND_UNDIGGED = null;
    private static final Color MORADO_BACKGROUND_DIGGED = null;

    private static String temaActual=CLARO;
    private static String [] fontColors =CLARO_COLORS;
    private static Color undiggedBackground=CLARO_BACKGROUND_UNDIGGED;
    private static Color diggedBackground=CLARO_BACKGROUND_DIGGED;

    public static String getDefinedTheme() {
        return temaActual;
    }
    public static String [] getFontColors(){return fontColors;}
    public static Color getUndiggedBackground(){return undiggedBackground;}
    public static Color getDiggedBackground(){return diggedBackground;}
    public static String getDefaultTheme(){return CLARO;}
    public static void changeTheme(String s){
        try {
            switch (s){
                case CLARO:
                    temaActual=CLARO;
                    fontColors=CLARO_COLORS;
                    undiggedBackground=CLARO_BACKGROUND_UNDIGGED;
                    diggedBackground=CLARO_BACKGROUND_DIGGED;
                    break;
                case DRACULA:
                    temaActual=DRACULA;
                    fontColors=DRACULA_COLORS;
                    undiggedBackground=DRACULA_BACKGROUND_UNDIGGED;
                    diggedBackground=DRACULA_BACKGROUND_DIGGED;
                    break;
                case MORADO:
                    temaActual=MORADO;
                    fontColors=MORADO_COLORS;
                    undiggedBackground=MORADO_BACKGROUND_UNDIGGED;
                    diggedBackground=MORADO_BACKGROUND_DIGGED;
                    break;
            }
            UIManager.setLookAndFeel(temaActual);
        }catch (Exception e) {
            throw new RuntimeException("Error al cambiar el tema, usando LaF por defecto");
        }

    }
}
