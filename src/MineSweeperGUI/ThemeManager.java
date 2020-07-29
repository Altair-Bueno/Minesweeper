package MineSweeperGUI;

import javax.swing.*;

public class ThemeManager {
    //Temas disponibles
    public static final String DRACULA="com.formdev.flatlaf.FlatDarculaLaf";
    public static final String CLARO="com.formdev.flatlaf.FlatLightLaf";
    public static final String MORADO="com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme";

    private static String temaActual=CLARO;

    public static String getDefinedTheme() {
        return temaActual;
    }
    public static void changeTheme(String s) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel(s);
            temaActual=s;
        }catch (Exception e) {
            throw new RuntimeException("Error al cambiar el tema, usando LaF por defecto");
        }

    }
}
