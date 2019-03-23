import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OptionPanel extends JPanel {

    private MenuButton start;
    private MenuButton settings;
    private BufferedImage image;
    private JLabel pic;

    public OptionPanel() {
        start = new MenuButton("play");
        settings = new MenuButton("settings");
        start.setToolTipText("Start a standard game");
//        start.setOpaque(false);
        settings.setToolTipText("Settings");
        setBackground(Color.CYAN);

        try {
            image = ImageIO.read(new File("assets/logo_chess.png"));
        } catch (IOException ex) {

        }

        pic = new JLabel(new ImageIcon(image));
//        pic.setToolTipText("This is a tool tip");
        pic.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        setLayout(new GridBagLayout());
        add(pic);
        c.gridy = 1;
        add(start, c);
        c.gridy = 2;
        add(settings, c);
    }

    public MenuButton getStart() {
        return start;
    }
}
