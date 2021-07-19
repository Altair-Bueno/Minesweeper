package manager;

import java.net.URL;

public class Loader {
    private interface Resource {
        String getPath();
    }
    public enum Icon implements Resource{
        APPICON("icons/icon.png"),
        SMALLAPPICON("icons/smallIcon.png"),
        FLAGICON("icons/flag.png"),
        CLOCKICON("icons/time.png"),
        MINEICON("icons/mineButton.png"),
        EXPLOSION("icons/explosion.png"),
        CONFFETI("icons/confetti.png");
        private final String path;

        Icon(String s) {
            path = s;
        }
        @Override
        public String getPath() {
            return path;
        }
    }
    public enum HTMLDoc implements Resource {
        ABOUT_PAGE ("htmldocs/About.html"),
        HELP_PAGE ("htmldocs/Help.html");
        private final String path;

        HTMLDoc(String s) {
            path = s;
        }
        @Override
        public String getPath() {
            return path;
        }
    }
    public enum SoundFiles implements Resource {
        DIG_SOUND("sound/Dig_Sound_1.wav"),
        LOOSE_SOUND("sound/Lose_Sound_1.wav"),
        MENU_START_SOUND("sound/Menu_Init_Sound_1.wav"),
        MINE_SOUND("sound/Mine_Explosion_Sound_1.wav"),
        WIN_SOUND("sound/Win_Sound_1.wav"),
        FLAG_SOUND("sound/Flag_Deploy_Sound_1.wav");
        private final String path;

        SoundFiles(String s) {
            path = s;
        }
        @Override
        public String getPath() {
            return path;
        }
    }

    public static String getAPPNAME() {
        return "Mine Sweeper";
    }

    public static URL getResourceURL(Resource path) {
        return ClassLoader
                .getSystemClassLoader()
                .getResource(path.getPath());
    }
}
