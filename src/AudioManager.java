import javax.sound.sampled.*;
import java.io.File;

class AudioManager {
    private static File clickFile = new File("assets/219069__annabloom__click1.wav");
    private static File boom = new File("assets/250712__aiwha__explosion.wav");
    private static File teleport = new File("assets/448226__inspectorj__explosion-8-bit-01.wav");
    private static File uke_song = new File("assets/bensound-ukulele.wav");

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

    static AudioInputStream musicStream;

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
            clip.stop();
            musicStream = null;
        }
    }

    void playBoom() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(boom);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    void playTele() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(teleport);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
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

    //public static

}
