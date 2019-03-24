import javax.swing.*;
import java.awt.*;

/**
 * This will be the panel containing the chess board and is where the game is played.
 */

public class BoardPanel extends JPanel {

    private int width;      //Represents the max number of columns.
    private int height;     //Represents the max number of rows.
    private BoardButton[][] space;

    public BoardPanel(int w, int h, Board board) {
        super(new GridLayout(w, h));
        width = w;
        height = h;
        setPreferredSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(500, 500));
        space = new BoardButton[width][height];
        RandomColorTile color = new RandomColorTile();

        new TextActuator().printBoard(board); //TextActuator as board debug print

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                space[j][i] = new BoardButton(j, i, color, board.getSpace(i, j).getPiece());
                add(space[j][i]);
                //TODO: Set the icon of the space to whatever piece is on it. Every time a piece is moved make sure to change the icons.
            }
        }
    }

//
//    public void setIcons() {
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                boardPanel.getButton(j, i).setNewIcon(board.getSpace(j, i).getPiece());
//            }
//        }
//
//    }

    public BoardButton getButton(int x, int y) {
        return space[x][y];
    }
}
