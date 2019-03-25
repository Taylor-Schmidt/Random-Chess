import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends OptionPanel {
    private BufferedImage image;
    private JLabel logo;

    public MainMenuPanel(){
        super();

        try {
            image = ImageIO.read(new File("assets/logo_chess.png"));
        } catch (IOException ex) {
            System.err.println("Could not find logo file.");
        }

        logo = new JLabel(new ImageIcon(image));

        logo.setOpaque(false);

        addComponent(0, logo);

        addButton(new MenuButton("play", "Start a standard game"));
        addButton(new MenuButton("settings", "Settings"));
    }
}
