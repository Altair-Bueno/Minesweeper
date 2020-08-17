package MineSweeperResources;

import javax.sound.sampled.*;
import java.net.URL;

public class MineSweeperJukeBox {
    public static Clip play(URL fileURL) {
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
            throw new ResourceException("Couldn't play sound " + fileURL + "\n"
                    + e.getMessage());
        }
    }
}
