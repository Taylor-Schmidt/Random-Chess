import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {
    boolean hasAlt = false;
    ImageIcon icon;
    ImageIcon iconPush;
    ImageIcon altIcon;
    ImageIcon altIconPush;
    private boolean isToggled = false;

    ImageButton(){}

    ImageButton(String iconAddr, String iconPushAddr) {
        this(iconAddr, iconPushAddr, null, null, false);
    }

    ImageButton(String iconAddr, String iconPushAddr, String altIconAddr, String altIconPushAddr, boolean hasAlt) {
        this.hasAlt = hasAlt;

        ImageManager imageManager = ImageManager.getInstance();

        icon = imageManager.getImage(iconAddr);
        iconPush = imageManager.getImage(iconPushAddr);
        altIcon = imageManager.getImage(altIconAddr);
        altIconPush = imageManager.getImage(altIconPushAddr);

        updateIcon();
    }

    void updateIcon(){
        setIcon(icon);
        setPressedIcon(iconPush);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(75, 75));
    }

    void toggle() {
        if (hasAlt) {
            if (isToggled) {
                setIcon(icon);
                setPressedIcon(iconPush);
            } else {
                setIcon(altIcon);
                setPressedIcon(altIconPush);
            }

            isToggled = !isToggled;
        }
    }


}
