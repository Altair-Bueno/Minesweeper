import MineSweeperResources.MineSweeperJukeBox;
import MineSweeperResources.MineSweeperResourceManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
        final JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.YELLOW);

        final JPanel container = new JPanel(new GridBagLayout());
        container.add(innerPanel);
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(innerPanel, container);
            }
        });
        final JFrame frame = new JFrame("AspectRatio");
        frame.getContentPane().add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension(size, size));
        container.revalidate();
    }
}
