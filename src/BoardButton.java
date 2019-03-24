import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * These are the buttons for the spaces on the board.
 */
public class BoardButton extends JButton {

    private int xPos;      // x coordinate of the button.
    private int yPos;      // y coordinate of the button.
    private ImageIcon pieceIcon;

    public BoardButton(int x, int y, RandomColorTile c) {
        super();
        xPos = x;
        yPos = y;

        //Creates checkered effect
        if (0 == ((xPos + yPos) % 2)) {
            setBackground(c.getLightColor());
        } else {
            setBackground(c.getDarkColor());
        }
    }

    public BoardButton(int x, int y, RandomColorTile c, Piece p){
        this(x, y, c);
        setNewIcon(p);
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setNewIcon(Piece p) {
        if (p != null) {
            switch (p.getType()) {
                case PAWN:
                    pieceIcon = new ImageIcon("assets/pawn_blue.png");
                case BISHOP:
                    break;
                case KING:
                    break;
                case QUEEN:
                    break;
                case ROOK:
                    break;
                case KNIGHT:
                    break;
            }
//
//            if (pieceIcon != null) {
//                setIcon(getScaledIcon(icon));
//            }
            updateUI();
        }
    }

    private Icon getScaledIcon(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = null;
//            newImage = image.getScaledInstance((int) (tileHeight * .8), (int) (tileHeight * .8), Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pieceIcon != null) {
//            System.out.println(getHeight() + "x" + getWidth());
            g.drawImage(pieceIcon.getImage(), (int) (getWidth() * 0.1), (int) (getHeight()* 0.1), (int) (getWidth() * 0.8), (int) (getHeight() * 0.8), this);
        }
    }

    public void addListener() {
        addActionListener(e -> {
            //TODO: Use this to program functionality of buttons.
            System.out.println("Clicked piece at " + xPos + " " + yPos);
        });
    }
}
