import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel {
    private JLabel icon = new JLabel();
    private static ImageManager imageManager = ImageManager.getInstance();
    private static final ImageIcon black = imageManager.getScaledImage("pawn_blue", 50, 50, 0);
    private static final ImageIcon white = imageManager.getScaledImage("pawn_red", 50, 50, 0);
    private static final ImageIcon turnIcon = imageManager.getImage("turn_word");

    private String color;

    InfoPanel(String color) {
        super();

        setBackground(ColorGenerator.backgroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel text = new JLabel(turnIcon);
        add(text, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.ipadx = 10;

        this.color = color;

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

    void toggleColor(){
        if (color.equals("black")) {
            color = "white";
        } else {
            color = "black";
        }

        setColor(color);
    }
}
