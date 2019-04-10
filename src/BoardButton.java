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

    private static BufferedImage[] lightGrassTiles;
    private static BufferedImage[] darkGrassTiles;
    private static BufferedImage[] lightDirtTiles;
    private static BufferedImage[] darkDirtTiles;
    private static BufferedImage bombSprite;
    private static BufferedImage morphSprite;

    static {
        try {
            lightGrassTiles = new BufferedImage[]{
                    ImageIO.read(new File("assets/board_tile_full.png")),
                    ImageIO.read(new File("assets/board_tile_full3.png"))
            };
            darkGrassTiles = new BufferedImage[]{
                    ImageIO.read(new File("assets/board_tile_full2.png")),
                    ImageIO.read(new File("assets/board_tile_full4.png"))
            };
            lightDirtTiles = new BufferedImage[]{
                    ImageIO.read(new File("assets/board_tile_empty_dirt1.png")),
                    ImageIO.read(new File("assets/board_tile_empty_dirt3.png")),
            };
            darkDirtTiles = new BufferedImage[]{
                    ImageIO.read(new File("assets/board_tile_empty_dirt2.png")),
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



            if (!highlighted) {

                if (((xPos + yPos) % 2) == 0) {
                    drawBackground(g, lightGrassTiles[random % lightGrassTiles.length]);
                } else {
                    drawBackground(g, darkGrassTiles[random % darkGrassTiles.length]);
                }

                if (space.getEffect() != null) {
                    BufferedImage effectIcon;
                    switch (space.getEffect().getType()) {
                        default:
                        case Bomb:
                            effectIcon = bombSprite;
                            break;
                        case SwitchPiece:
                            effectIcon =  morphSprite;
                            break;
                    }

                    double widthToHeightRatio = effectIcon.getWidth() / (effectIcon.getHeight() * 1.0); //float div with ints
                    int width;
                    int height;
                    int x;
                    int y;

                    double effectPaddingRatio = 0.2;

                    double internalSize = 1 - (2 * effectPaddingRatio);
                    if (widthToHeightRatio > 1) {//width is bigger
                        width = (int) (getWidth() * internalSize);
                        height = (int) (width / widthToHeightRatio);
                        x = (int) (getWidth() * effectPaddingRatio);
                        y = (int) (getHeight() * effectPaddingRatio + (getHeight() * internalSize - height) / 2.0);
                    } else if (widthToHeightRatio < 1) {
                        height = (int) (getHeight() * internalSize);
                        width = (int) (height * widthToHeightRatio);
                        x = (int) (getWidth() * effectPaddingRatio + (getWidth() * internalSize - width) / 2.0);
                        y = (int) (getHeight() * effectPaddingRatio);
                    } else {
                        width = (int) (getWidth() * internalSize);
                        height = (int) (getHeight() * internalSize);
                        x = (int) (getWidth() * effectPaddingRatio);
                        y = (int) (getHeight() * effectPaddingRatio);
                    }
                    g.drawImage(effectIcon, x, y, width, height, this);
                }
            } else {
                setBackground(selectedColor);
            }



        } else {
            if (((xPos + yPos) % 2) == 0) {
                drawBackground(g, lightDirtTiles[random % lightDirtTiles.length]);
            } else {
                drawBackground(g, darkDirtTiles[random % darkDirtTiles.length]);
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
