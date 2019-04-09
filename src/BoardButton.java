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

    private static BufferedImage[] lights;
    private static BufferedImage[] darks;
    private static BufferedImage[] dirts;
    private static BufferedImage bombSprite;
    private static BufferedImage morphSprite;

    static {
        try {
            lights = new BufferedImage[]{
                    ImageIO.read(new File("assets/board_tile_full.png")),
                    ImageIO.read(new File("assets/board_tile_full.png"))
            };
            darks = new BufferedImage[] {
                    ImageIO.read(new File("assets/board_tile_empty.png"))
            };
            dirts = new BufferedImage[]{
//                    ImageIO.read(new File("assets/board_tile_empty_dirt1.png")),
//                    ImageIO.read(new File("assets/board_tile_empty_dirt2.png")),
                    ImageIO.read(new File("assets/board_tile_empty_dirt3.png")),
                    ImageIO.read(new File("assets/board_tile_empty_dirt4.png")),
            };
            bombSprite = ImageIO.read(new File("assets/board_tile_bomb.png"));
            morphSprite = ImageIO.read(new File("assets/board_tile_morph.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    private BoardPanel parent;
    Space space;
    private final Color selectedColor = new Color(0, 255, 0);
    private Color backgroundColor;
    private Random rand = new Random();
    private int random = rand.nextInt(Integer.MAX_VALUE);


    private boolean highlighted = false;

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

        if (space == null) {
            backgroundColor = Color.decode("#78d5e1");
        }
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

        if (space != null) {
            if (space.getEffect() != null){
                switch (space.getEffect().getType()){
                    case Bomb:
                        drawBackground(g, bombSprite);
                        break;
                    case SwitchPiece:
                        drawBackground(g, morphSprite);
                        break;
                }
            } else {
                if (!highlighted) {

                    if (0 == ((xPos + yPos) % 2)) {
                        drawBackground(g, lights[random % lights.length]);
                    } else {
                        drawBackground(g, darks[random % darks.length]);
                    }
                } else {
                    setBackground(selectedColor);
                }
            }
        } else {
            drawBackground(g, dirts[random % dirts.length]);
        }

        if (pieceIcon != null) {
//            System.out.println(getHeight() + "x" + getWidth());
            double widthToHeightRatio = pieceIcon.getIconWidth() / (pieceIcon.getIconHeight() * 1.0); //float div with ints
            int width;
            int height;
            int x;
            int y;

            double internalSize = 1 - (2 * paddingRatio);
            if (widthToHeightRatio > 1) {//width is bigger
                width = (int) (getWidth() * internalSize);
                height = (int) (width / widthToHeightRatio);
                x = (int) (getWidth() * paddingRatio);
                y = (int) (getHeight() * paddingRatio + (getHeight() * internalSize - height) / 2.0);
            } else if (widthToHeightRatio < 1) {
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

    private void drawBackground(Graphics g, BufferedImage image) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }


    public void setHighlight(boolean highLighted) {
        this.highlighted = highLighted;
        if (highLighted) {
            setBackground(selectedColor);

        } else {
            setBackground(backgroundColor);

        }
    }
}
