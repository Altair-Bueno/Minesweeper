package MineSweeperJavaResources;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MineSweeperResourceManager {

    private static final String APPNAME="Mine Sweeper";

    private static final String APPICON ="MineSweeperJavaResources/ImageResources/icon.png";
    private static final String SMALLAPPICON="MineSweeperJavaResources/ImageResources/smallIcon.png";
    private static final String FLAGICON="MineSweeperJavaResources/ImageResources/flag.png";
    private static final String CLOCKICON="MineSweeperJavaResources/ImageResources/time.png";
    private static final String MINAICON="MineSweeperJavaResources/ImageResources/mineButton.png";


    public static String getAPPNAME() {
        return APPNAME;
    }

    public static ImageIcon getAppIcon() {
        try {
            return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.APPICON)));
        }catch (Exception e){
            throw new ResourceNotFoundException(APPICON+ " Not found: " + e.getMessage());
        }
    }
    public static ImageIcon getSmallAppIcon(){
        try {
            return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.SMALLAPPICON)));
        }catch (Exception e){
            throw new ResourceNotFoundException(SMALLAPPICON+ " Not found: " + e.getMessage());
        }
    }
    public static ImageIcon getFlagIcon(){
        try {
            return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.FLAGICON)));
        }catch (Exception e){
            throw new ResourceNotFoundException(FLAGICON+ " Not found: " + e.getMessage());
        }
    }
    public static ImageIcon getClockIcon(){
        try {
            return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(MineSweeperResourceManager.CLOCKICON)));
        }catch (Exception e){
            throw new ResourceNotFoundException(CLOCKICON+ " Not found: " + e.getMessage());
        }
    }
    public static ImageIcon getMinaIcon(){
        try {
            return new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream( MineSweeperResourceManager.MINAICON)));
        }catch (Exception e){
            throw new ResourceNotFoundException(MINAICON+ " Not found: " + e.getMessage());
        }
    }
}
