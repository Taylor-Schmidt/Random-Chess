import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class MenuButton extends JButton {
    private String imageName;
    private ImageIcon icon;

    public MenuButton(String title){
        this(title, null);
    }

    public MenuButton(String title, String description) {
        super();
        imageName = title;

        setToolTipText(description);

        icon = new ImageIcon("assets/" + imageName + "_button.png");
        setIcon(icon);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addActionListener(e -> {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/219069__annabloom__click1.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
            icon = new ImageIcon("assets/" + imageName + "_button_push.png");
            setIcon(icon);
            updateUI();
        });
    }
}
