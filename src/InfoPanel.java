import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel icon = new JLabel();
    private static final ImageIcon black = new ImageIcon("assets/pawn_blue.png");
    private static final ImageIcon white = new ImageIcon("assets/pawn_red.png");
    private static final ImageIcon turnIcon = new ImageIcon("assets/turn_word.png");

    InfoPanel(String color) {
        super();

        setBackground(Color.CYAN);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel text = new JLabel(turnIcon);
        add(text, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.ipadx = 10;

        setColor(color);
        add(icon, gc);


    }

    void setColor(String color){
        if (color.equals("black")) {
            icon.setIcon(black);
        } else {
            icon.setIcon(white);
        }
        icon.updateUI();
    }
}
