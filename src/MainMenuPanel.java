import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends OptionPanel {

    public MainMenuPanel(){
        super();

        JLabel logo = new JLabel(new ImageIcon("assets/logo_chess.png"));
        logo.setOpaque(false);

        addComponent(0, logo);

        addButton(new MenuButton("play", "Start a standard game"));
        addButton(new MenuButton("settings", "Settings"));
    }
}
