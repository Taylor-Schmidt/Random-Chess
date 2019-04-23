import javax.swing.*;

class MenuButton extends JButton {

    MenuButton(String title) {
        super();

        ImageIcon icon = new ImageIcon("assets/" + title + "_button.png");
        setIcon(icon);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        ImageIcon pressedIcon = new ImageIcon("assets/" + title + "_button_push.png");
        setPressedIcon(pressedIcon);

        addActionListener(e -> AudioManager.getInstance().playClick());
    }
}
