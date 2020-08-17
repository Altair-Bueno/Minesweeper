import MineSweeperResources.MineSweeperJukeBox;
import MineSweeperResources.MineSweeperResourceManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class TestMain {
    /*
    public static void main(String[] args) {
        // Test code for colors
        ThemeManager.setTheme(ThemeManager.MORADO);
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int i=0;
        for (String color:ThemeManager.getFontColors()){
            if (color!=null) {
                JButton temp= new JButton(
                        "<html><b><font size=6 color="+color+">"+i+"</font></b></html>");
                temp.setBackground(ThemeManager.getDiggedBackground());
                jPanel.add(temp);
                i++;
            }
        }
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
    }
     */
    /*
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
        Mixer.Info[] mixInfo= AudioSystem.getMixerInfo();
        Mixer mixer =AudioSystem.getMixer(mixInfo[4]);
        for (Mixer.Info i : mixInfo){
            System.out.println(i);
        }
        DataLine.Info dataLineInfo= new DataLine.Info(Clip.class,null);
        Clip clip =(Clip)mixer.getLine(dataLineInfo);
        URL soundURL = TestMain.class.getResource("MineSweeperJavaResources/Sounds/Win_Sound_1.wav");
        AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(soundURL);
        clip.open(audioInputStream);
        clip.start();
        Thread.sleep(700000);
    }

     */
    /*
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/compux72/Downloads/Quarantined Feelings (prod. Kelife).wav"));
        AudioInputStream audio = AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream("MineSweeperJavaResources/Sounds/Win_Sound_1.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
        JOptionPane.showConfirmDialog(null,"hola");
    }


     */
    public static void main(String[] args) {
        MineSweeperJukeBox.play(MineSweeperResourceManager.getResourceURL("MineSweeperResources/Sounds/Flag_Deploy_Sound_1.wav"));
    }
}
