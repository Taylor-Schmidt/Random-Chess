import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * These are the buttons for the spaces on the board.
 */
public class BoardButton extends JButton {

    private int xPos;      // x coordinate of the button.
    private int yPos;      // y coordinate of the button.
    private ImageIcon pieceIcon;
    private double paddingRatio;

    private BufferedImage lGrass1;
    private BufferedImage lGrass2;
    private BufferedImage lGrass3;
    private BufferedImage lGrass4;

    private BufferedImage dGrass1;
    private BufferedImage dGrass2;
    private BufferedImage dGrass3;
    private BufferedImage dGrass4;
    private BufferedImage dGrass5;
    private BufferedImage Boarder1;

    private BoardPanel parent;
    Space space;
    private final Color selectedColor = new Color(0, 255, 0);
    private Color backgroundColor;
    Random rand = new Random();
    int random = rand.nextInt(4);
    int random2 = rand.nextInt(5);

//    public BoardButton(int x, int y, RandomColorTile c) {
//        this(x, y, c, null, 0.1);
//    }

    public BoardButton(BoardPanel parent, int x, int y, ColorGenerator c, Space s, Piece p) {
        this(parent, x, y, c, s, p, 0.1);
    }

    public BoardButton(BoardPanel parent, int x, int y, ColorGenerator c, Space space, Piece p, double paddingRatio) {
        super();
        this.parent = parent;
        xPos = x;
        yPos = y;
        this.paddingRatio = paddingRatio;

        //Creates checkered effect

        if (space == null)
            backgroundColor = Color.decode("#78d5e1");
        this.space = space;
        setBackground(backgroundColor);
        setNewIcon(p);
        setOpaque(true);
        setBorderPainted(false);
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
        try {
            lGrass1 = ImageIO.read(new File("assets/Layer 2.png"));
            lGrass2 = ImageIO.read(new File("assets/Layer 4.png"));
            lGrass3 = ImageIO.read(new File("assets/Layer 6.png"));
            lGrass4 = ImageIO.read(new File("assets/Layer 8.png"));
            dGrass1 = ImageIO.read(new File("assets/Layer 1.png"));
            dGrass2 = ImageIO.read(new File("assets/Layer 3.png"));
            dGrass3 = ImageIO.read(new File("assets/Layer 5.png"));
            dGrass4 = ImageIO.read(new File("assets/Layer 7.png"));
            dGrass5 = ImageIO.read(new File("assets/Layer 9.png"));
            Boarder1 = ImageIO.read(new File("assets/Boarder1.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        if (space != null) {
            if (0 == ((xPos + yPos) % 2)) {
                if (random == 0) {
                    g.drawImage(lGrass1, 0, 0, 40, 40, this);
                } else if (random == 1) {
                    g.drawImage(lGrass2, 0, 0, 40, 40, this);
                } else if (random == 2) {
                    g.drawImage(lGrass3, 0, 0, 40, 40, this);
                } else {
                    g.drawImage(lGrass4, 0, 0, 40, 40, this);
                }
            } else {
                if (random2 == 0) {
                    g.drawImage(dGrass1, 0, 0, 40, 40, this);
                } else if (random2 == 1) {
                    g.drawImage(dGrass2, 0, 0, 40, 40, this);
                } else if (random2 == 2) {
                    g.drawImage(dGrass3, 0, 0, 40, 40, this);
                } else if (random2 == 3) {
                    g.drawImage(dGrass4, 0, 0, 40, 40, this);
                }else
                    g.drawImage(dGrass5, 0, 0, 40, 40, this);
            }
        }
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
