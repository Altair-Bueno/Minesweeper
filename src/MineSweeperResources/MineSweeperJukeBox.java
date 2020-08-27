package MineSweeperResources;

import javax.sound.sampled.*;
import java.net.URL;

public class MineSweeperJukeBox {
    private static boolean playMusic = true;

    public static Clip play(URL fileURL) {
        if (!playMusic) return null;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileURL);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip clip;
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start(); //starts the daemon thread
            return clip; //Returns the daemon thread
        } catch (Exception e) {
            System.err.println("Couldn't play sound: " + "\n");
            e.printStackTrace();
        }
        return null;
    }

    public static void toggleSoud() {
        playMusic = !playMusic;
    }

    public static boolean canPlayMusic() {
        return playMusic;
    }
}
