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
    private double paddingRatio;

    public BoardButton(int x, int y, RandomColorTile c) {
        this(x, y, c, null, 0.1);
    }

    public BoardButton(int x, int y, RandomColorTile c, Piece p){
        this(x, y, c, p, 0.1);
    }

    public BoardButton(int x, int y, RandomColorTile c, Piece p, double paddingRatio){
        super();
        xPos = x;
        yPos = y;
        this.paddingRatio = paddingRatio;

        //Creates checkered effect
        if (0 == ((xPos + yPos) % 2)) {
            setBackground(c.getLightColor());
        } else {
            setBackground(c.getDarkColor());
        }
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
            double widthToHeightRatio = pieceIcon.getIconWidth() / (pieceIcon.getIconHeight() * 1.0); //float div with ints
            int width;
            int height;
            int x;
            int y;

            double internalSize = 1 - (2 * paddingRatio);
            if (widthToHeightRatio > 1){//width is bigger
                width = (int) (getWidth() * internalSize);
                height = (int) (width / widthToHeightRatio);
                x = (int) (getWidth() * paddingRatio);
                y = (int) (getHeight() * paddingRatio + (getHeight() * internalSize - height) / 2.0);
            } else if (widthToHeightRatio < 1){
                height = (int) (getHeight() * internalSize);
                width = (int) (height * widthToHeightRatio);
                x = (int) (getWidth() * paddingRatio + (getWidth() * internalSize - width) / 2.0);
                y = (int) (getHeight() * paddingRatio);
            } else {
                width = (int) (getWidth() * internalSize);
                height = (int) (getHeight() * internalSize);
                x = (int) (getWidth() * paddingRatio);
                y = (int) (getHeight() * paddingRatio);
            }

            g.drawImage(pieceIcon.getImage(), x, y, width, height, this);
        }
    }

    public void addListener() {
        addActionListener(e -> {
            //TODO: Use this to program functionality of buttons.
            System.out.println("Clicked piece at " + xPos + " " + yPos);
        });
    }
}