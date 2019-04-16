import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {
    boolean hasAlt = false;
    ImageIcon icon;
    ImageIcon iconPush;
    ImageIcon altIcon;
    ImageIcon altIconPush;
    private boolean isToggled = false;
    String toolTipText;
    String altToolTipText;

    ImageButton(){}

    ImageButton(String iconAddr, String iconPushAddr, String toolTipText) {
        this(iconAddr, iconPushAddr, toolTipText, null, null, null, false);
    }

    ImageButton(String iconAddr, String iconPushAddr, String toolTipText, String altIconAddr, String altIconPushAddr, String altToolTipText, boolean hasAlt) {
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
        setToolTipText(toolTipText);
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
                setToolTipText(toolTipText);
            } else {
                setIcon(altIcon);
                setPressedIcon(altIconPush);
                setToolTipText(altToolTipText);
            }

            isToggled = !isToggled;
        }
    }


}
