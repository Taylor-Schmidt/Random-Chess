import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.Border;

public class MenuButton extends JButton {
    private String imageName;
    private ImageIcon icon;

    public MenuButton(String s) {
        super();
        imageName = s;
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
