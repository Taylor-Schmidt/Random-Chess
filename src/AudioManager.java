import javax.sound.sampled.*;
import java.io.File;

class AudioManager {
    private File clickFile = new File("assets/219069__annabloom__click1.wav");
    private File boom = new File("assets/250712__aiwha__explosion.wav");
    private File teleport = new File("assets/448226__inspectorj__explosion-8-bit-01.wav");
    private File uke_song = new File("assets/bensound-ukulele.wav");

    private static final AudioManager audioManager = new AudioManager();

    static AudioInputStream musicStream;

    private GameSettings gameSettings = GameSettings.getInstance();

    private AudioManager() {
    }

    static AudioManager getInstance() {
        return audioManager;
    }

    void playClick() {
        playSound(clickFile);
    }

    private void playSound(File file) {
        playSound(file, 0);
    }

    private void playSound(File file, float gain) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    void playMusic() {
        if (musicStream == null) {
            try {
                musicStream = AudioSystem.getAudioInputStream(uke_song);
                Clip clip = AudioSystem.getClip();
                clip.open(musicStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
                clip.start();
                clip.addLineListener(e -> {
                    if (e.getType() == LineEvent.Type.STOP) {
                        musicStream = null;
                        playMusic();
                    }
                });
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }

    void stopMusic() {
        if (musicStream != null) {
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            if (clip != null) {
                clip.stop();
            }
            musicStream = null;
        }
    }

    void playBoom() {
        playSound(boom);
    }

    void playTele() {
        playSound(teleport, -25);
    }

    //Makes sure audio streams are open
    void playTestSound() {
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
