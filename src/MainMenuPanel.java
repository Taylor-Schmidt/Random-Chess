import javax.swing.*;

public class MainMenuPanel extends OptionPanel {

    public MainMenuPanel(){
        super();

        ImageManager im = ImageManager.getInstance();

        JLabel logo = new JLabel(im.getImage("logo_chess"));
        logo.setOpaque(false);

        addComponent(0, logo);

        addButton(new MenuButton("play_game", "Start a standard game"));
        addButton(new MenuButton("settings", "Settings"));
    }
}
