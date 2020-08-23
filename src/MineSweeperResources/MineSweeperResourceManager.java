package MineSweeperResources;

import java.net.URL;

public class MineSweeperResourceManager {

    private static final String APPNAME = "Mine Sweeper";

    //Icons
    public static final String APPICON = "MineSweeperResources/Icons/icon.png";
    public static final String SMALLAPPICON = "MineSweeperResources/Icons/smallIcon.png";
    public static final String FLAGICON = "MineSweeperResources/Icons/flag.png";
    public static final String CLOCKICON = "MineSweeperResources/Icons/time.png";
    public static final String MINEICON = "MineSweeperResources/Icons/mineButton.png";
    public static final String EXPLOSION = "MineSweeperResources/Icons/explosion.png";
    public static final String CONFFETI = "MineSweeperResources/Icons/confetti.png";

    //HTML documents
    public static final String ABOUT_PAGE = "MineSweeperResources/HTMLDocuments/About.html";
    public static final String HELP_PAGE = "MineSweeperResources/HTMLDocuments/Help.html";

    //Sound files
    public static final String DIG_SOUND = "MineSweeperResources/Sounds/Dig_Sound_1.wav";
    public static final String FLAG_SOUND = "MineSweeperResources/Sounds/Flag_Deploy_Sound_1.wav";
    public static final String LOOSE_SOUND = "MineSweeperResources/Sounds/Lose_Sound_1.wav";
    public static final String MENU_START_SOUND = "MineSweeperResources/Sounds/Menu_Init_Sound_1.wav";
    public static final String MINE_SOUND = "MineSweeperResources/Sounds/Mine_Explosion_Sound_1.wav";
    public static final String WIN_SOUND = "MineSweeperResources/Sounds/Win_Sound_1.wav";


    public static String getAPPNAME() {
        return APPNAME;
    }

    /*
        public static ImageIcon getAppIcon() {
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.APPICON)));
            } catch (Exception e) {
                throw new ResourceException(APPICON + " Not found: " + e.getMessage());
            }
        }

        public static ImageIcon getSmallAppIcon() {
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.SMALLAPPICON)));
            } catch (Exception e) {
                throw new ResourceException(SMALLAPPICON + " Not found: " + e.getMessage());
            }
        }

        public static ImageIcon getFlagIcon() {
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.FLAGICON)));
            } catch (Exception e) {
                throw new ResourceException(FLAGICON + " Not found: " + e.getMessage());
            }
        }

        public static ImageIcon getClockIcon() {
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.CLOCKICON)));
            } catch (Exception e) {
                throw new ResourceException(CLOCKICON + " Not found: " + e.getMessage());
            }
        }

        public static ImageIcon getMinaIcon() {
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.MINEICON)));
            } catch (Exception e) {
                throw new ResourceException(MINEICON + " Not found: " + e.getMessage());
            }
        }
        public static ImageIcon getExplosionIcon(){
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.EXPLOSION)));
            } catch (Exception e) {
                throw new ResourceException(EXPLOSION + " Not found: " + e.getMessage());
            }
        }
        public static ImageIcon getConffetiIcon(){
            try {
                return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.CONFFETI)));
            } catch (Exception e) {
                throw new ResourceException(CONFFETI + " Not found: " + e.getMessage());
            }
        }
        public static URL getAboutPage(){
            return ClassLoader.getSystemResource(ABOUT_PAGE);
        }
        public static URL getHelpPage(){
            return ClassLoader.getSystemResource(HELP_PAGE);
        }

     */
    public static URL getResourceURL(String path) {
        return ClassLoader.getSystemResource(path);
    }
}
