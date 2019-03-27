import javax.swing.*;
import java.awt.*;

/**
 * These are the buttons for the spaces on the board.
 */
public class BoardButton extends JButton {

    private int xPos;      // x coordinate of the button.
    private int yPos;      // y coordinate of the button.
    private ImageIcon pieceIcon;
    private double paddingRatio;

    private BoardPanel parent;

    private final Color selectedColor = new Color(0, 255, 0);
    private Color backgroundColor;

//    public BoardButton(int x, int y, RandomColorTile c) {
//        this(x, y, c, null, 0.1);
//    }

    public BoardButton(BoardPanel parent, int x, int y, ColorGenerator c, Space s, Piece p){
        this(parent, x, y, c, s, p, 0.1);
    }

    public BoardButton(BoardPanel parent, int x, int y, ColorGenerator c, Space space, Piece p, double paddingRatio){
        super();
        this.parent = parent;
        xPos = x;
        yPos = y;
        this.paddingRatio = paddingRatio;

        //Creates checkered effect
        if(space==null) {
            backgroundColor = Color.BLACK;
        } else {
            if (0 == ((xPos + yPos) % 2)) {
                backgroundColor = c.getLightColor();
            } else {
                backgroundColor = c.getDarkColor();
            }
        }

        setBackground(backgroundColor);
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
            String color = p.getColor().equals("black") ? "blue" : "red";

            switch (p.getType()) {
                case PAWN:
                    pieceIcon = new ImageIcon("assets/pawn_" + color + ".png");
                    break;
                case BISHOP:
                    pieceIcon = new ImageIcon("assets/bishop_" + color + ".png");
                    break;
                case KING:
                    pieceIcon = new ImageIcon("assets/king_" + color + ".png");
                    break;
                case QUEEN:
                    pieceIcon = new ImageIcon("assets/queen_" + color + ".png");
                    break;
                case ROOK:
                    pieceIcon = new ImageIcon("assets/rook_" + color + ".png");
                    break;
                case KNIGHT:
                    pieceIcon = new ImageIcon("assets/knight_" + color + ".png");
                    break;
            }
//
//            if (pieceIcon != null) {
//                setIcon(getScaledIcon(icon));
//            }
            updateUI();
        } else {
            pieceIcon = null;
        }
    }
/*
    private Icon getScaledIcon(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = null;
        newImage = image.getScaledInstance((int) (tileHeight * .8), (int) (tileHeight * .8), Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }*/

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

    public void setHighlight(boolean highLighted){
        if (highLighted){
            setBackground(selectedColor);
        } else {
            setBackground(backgroundColor);
        }
    }
}
