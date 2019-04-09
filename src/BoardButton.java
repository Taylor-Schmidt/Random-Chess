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

    private static BufferedImage lGrass1;
    private static BufferedImage lGrass2;
    private static BufferedImage lGrass3;
    private static BufferedImage lGrass4;

    private static BufferedImage dGrass1;
    private static BufferedImage dGrass2;
    private static BufferedImage dGrass3;
    private static BufferedImage dGrass4;
    private static BufferedImage dGrass5;
    private static BufferedImage Boarder1;

    static {
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
            if (!highlighted) {

                if (0 == ((xPos + yPos) % 2)) {
                    switch (random % 4) {
                        case 0:
                            drawBackground(g, lGrass1);
                            break;
                        case 1:
                            drawBackground(g, lGrass2);
                            break;
                        case 2:
                            drawBackground(g, lGrass3);
                            break;
                        case 3:
                            drawBackground(g, lGrass4);
                            break;
                    }
                } else {
                    switch (random % 5) {
                        case 0:
                            drawBackground(g, dGrass1);
                            break;
                        case 1:
                            drawBackground(g, dGrass2);
                            break;
                        case 2:
                            drawBackground(g, dGrass3);
                            break;
                        case 3:
                            drawBackground(g, dGrass4);
                            break;
                        case 4:
                            drawBackground(g, dGrass5);
                            break;
                    }
                }
            } else {
                setBackground(selectedColor);
            }
        }

        if (space != null && space.getEffect() != null) {
            BufferedImage effectImage;
            if(space.getEffect().getType() == Effect.EffectType.Bomb) {

                try {
                    effectImage = ImageIO.read(new File("assets/board_tile_bomb.png"));
                    drawBackground(g, effectImage);
                    //System.out.println("Drew a boardButton with a bomb on it.");
                } catch (IOException e) { }
            }
            else if(space.getEffect().getType() == Effect.EffectType.SwitchPiece) {
                try {
                    effectImage = ImageIO.read(new File("assets/board_tile_morph.png"));
                    drawBackground(g, effectImage);
                    //System.out.println("Drew a boardButton with a bomb on it.");
                } catch (IOException e) { }
            }
            setNewIcon(space.getPiece());

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
