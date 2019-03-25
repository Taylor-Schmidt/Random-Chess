import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

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
        setSize(300, 100);
        Border b = new LineBorder(Color.WHITE, 0);
        setBorder(b);
//        setOpaque(false);
        setContentAreaFilled(false);
//        setBorderPainted(false);

        addActionListener(e -> {
            icon = new ImageIcon("assets/" + imageName + "_button_push.png");
            setIcon(icon);
            setOpaque(false);
            updateUI();
        });
    }
}
