import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This will be the panel containing the chess board and is where the game is played.
 */

public class BoardPanel extends JPanel {

    private int width;      //Represents the max number of columns.
    private int height;     //Represents the max number of rows.
    private BoardButton[][] space;

    private ArrayList<BoardButton> highlightedSpaces;

    public BoardPanel(int w, int h, Board board) {
        super(new GridLayout(w, h));
        width = w;
        height = h;
        setPreferredSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(500, 500));
        space = new BoardButton[width][height];
        ColorGenerator color = new ColorGenerator();

        new TextActuator().printBoard(board); //TextActuator as board debug print

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                space[j][i] = new BoardButton(this, j, i, color, board.getSpace(i, j).getPiece());
                add(space[j][i]);
                //TODO: Set the icon of the space to whatever piece is on it. Every time a piece is moved make sure to change the icons.
            }
        }
    }


    public void highlightSpaces(ArrayList<BoardButton> spacesToHighlight){
        highlightedSpaces = spacesToHighlight;
    }

    public void unhighlightSpaces(){
        for (BoardButton b: highlightedSpaces){

        }
    }

    public BoardButton getButton(int x, int y) {
        return space[x][y];
    }
}
