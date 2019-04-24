import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * These are the buttons for the spaces on the board.
 */
public class BoardButton extends JButton {

    private int xPos;      // x coordinate of the button.
    private int yPos;      // y coordinate of the button.
    private ImageIcon pieceIcon;
    private double paddingRatio;

    private static ImageIcon[] lightGrassTiles;
    private static ImageIcon[] darkGrassTiles;
    private static ImageIcon[] lightDirtTiles;
    private static ImageIcon[] darkDirtTiles;
    private static ImageIcon bombSprite;
    private static ImageIcon morphSprite;

    static {
        ImageManager im = ImageManager.getInstance();

        lightGrassTiles = new ImageIcon[]{
                im.getImage("board_tile_full3"),
                im.getImage("board_tile_full5"),
                im.getImage("board_tile_full7"),
                im.getImage("board_tile_full7"),
                im.getImage("board_tile_full7"),
                im.getImage("board_tile_full7")
        };
        darkGrassTiles = new ImageIcon[]{
                im.getImage("board_tile_full4"),
                im.getImage("board_tile_full6"),
                im.getImage("board_tile_full8"),
                im.getImage("board_tile_full8"),
                im.getImage("board_tile_full8"),
                im.getImage("board_tile_full8")
        };
        lightDirtTiles = new ImageIcon[]{
                im.getImage("board_tile_empty_dirt1"),
                im.getImage("board_tile_empty_dirt3"),
                im.getImage("board_tile_empty_dirt5"),
                im.getImage("board_tile_empty_dirt5"),
                im.getImage("board_tile_empty_dirt5")
        };
        darkDirtTiles = new ImageIcon[]{
                im.getImage("board_tile_empty_dirt2"),
                im.getImage("board_tile_empty_dirt4"),
                im.getImage("board_tile_empty_dirt6"),
                im.getImage("board_tile_empty_dirt6"),
                im.getImage("board_tile_empty_dirt6")
        };
        bombSprite = im.getImage("board_tile_bomb");
        morphSprite = im.getImage("board_tile_morph");
    }

    Space space;
    private final Color selectedColor = new Color(0, 255, 0);
    private Color backgroundColor;
    private Random rand = new Random();
    private int random = rand.nextInt(Integer.MAX_VALUE);


    private boolean highlighted = false;

//    public BoardButton(int x, int y, RandomColorTile c) {
//        this(x, y, c, null, 0.1);
//    }

    BoardButton(int x, int y, Space s, Piece p) {
        this(x, y, s, p, 0.1);
    }

    private BoardButton(int x, int y, Space space, Piece p, double paddingRatio) {
        super();
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

    void setNewIcon(Piece p) {
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
                    ImageIcon effectIcon;
                    switch (space.getEffect().getType()) {
                        default:
                        case Bomb:
                            effectIcon = bombSprite;
                            break;
                        case SwitchPiece:
                            effectIcon = morphSprite;
                            break;
                    }

                    double widthToHeightRatio = effectIcon.getIconWidth() / (effectIcon.getIconHeight() * 1.0); //float div with ints

                    double effectPaddingRatio = 0.2;

                    drawScaledImageIcon(g, widthToHeightRatio, effectPaddingRatio, effectIcon);
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

            drawScaledImageIcon(g, widthToHeightRatio, paddingRatio, pieceIcon);
        }


    }

    private void drawScaledImageIcon(Graphics g, double widthToHeightRatio, double paddingRatio, ImageIcon pieceIcon) {
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

    private void drawBackground(Graphics g, ImageIcon image) {
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
    }


    void setHighlight(boolean highLighted) {
        this.highlighted = highLighted;
        if (highLighted) {
            setBackground(selectedColor);

        } else {
            setBackground(backgroundColor);

        }
    }
}
