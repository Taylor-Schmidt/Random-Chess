import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

class AudioManager {
    private static File clickFile = new File("assets/219069__annabloom__click1.wav");

    private static final AudioManager audioManager = new AudioManager();

    private AudioManager() {
    }

    static AudioManager getInstance() {
        return audioManager;
    }

    void playClick() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clickFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //Makes sure audio streams are open
    void playTestSound(){
        File file = new File("assets/silence.wav");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(0.0f);
            clip.start();
            volume.setValue(1.0f);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
