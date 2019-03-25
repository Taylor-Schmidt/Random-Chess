import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This will be the panel containing the chess board and is where the game is played.
 */

public class BoardPanel extends JPanel {

    private int width;      //Represents the max number of columns.
    private int height;     //Represents the max number of rows.
    private BoardButton[][] space;

    private HashSet<Position> highlightedSpaces = new HashSet<>();

    public BoardPanel(int w, int h, Board board) {
        super(new GridLayout(w, h));
        width = w;
        height = h;
        setPreferredSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(500, 500));
        space = new BoardButton[height][width];
        ColorGenerator color = new ColorGenerator();

        new TextActuator().printBoard(board); //TextActuator as board debug print

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Piece piece = board.getSpace(i, j).getPiece();
                BoardButton button = new BoardButton(this, i, j, color, piece);

                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    System.out.println("Clicked piece at " + button.getXPos() + " " + button.getYPos());
                    if (highlightedSpaces.contains(new Position(finalI, finalJ))) {
                        //TODO: move piece and remove other piece if necessary

                    }
                    if (!highlightedSpaces.isEmpty()){
                        unhighlightSpaces();
                    }
                    if (piece != null){
                        highlightSpaces(piece.getAvailableMoves(board, finalI, finalJ));
                    }
                });

                add(button);
                space[i][j] = button;
                //TODO: Set the icon of the space to whatever piece is on it. Every time a piece is moved make sure to change the icons.
            }
        }
    }


    public void highlightSpaces(HashSet<Position> spacesToHighlight) {
        highlightedSpaces = spacesToHighlight;
        for (Position p : highlightedSpaces) {
            space[p.row][p.col].setHighlight(true);
        }
    }

    public void unhighlightSpaces() {
        for (Position p : highlightedSpaces) {
            space[p.row][p.col].setHighlight(false);
        }
        highlightedSpaces.clear();
    }

    public BoardButton getButton(int x, int y) {
        return space[x][y];
    }
}
