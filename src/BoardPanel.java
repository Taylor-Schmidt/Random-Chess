import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * This will be the panel containing the chess board and is where the game is played.
 */

public class BoardPanel extends JPanel {

    private int width;      //Represents the max number of columns.
    private int height;     //Represents the max number of rows.
    private BoardButton[][] boardButtons;

    private Position selectedPosition;
    private HashSet<Position> highlightedSpaces = new HashSet<>();

    public BoardPanel(int w, int h, Board board) {
        super(new GridLayout(w, h));
        width = w;
        height = h;
        setPreferredSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(650, 650));
        boardButtons = new BoardButton[height][width];
        ColorGenerator color = new ColorGenerator();

        new TextActuator().printBoard(board); //TextActuator as board debug print

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Piece piece;
                Space s;
                if(board.getSpace(i,j)!=null) {
                    piece = board.getSpace(i, j).getPiece();
                    s = board.getSpace(i,j);
                }
                else {
                    piece = null;
                    s = null;
                }
                BoardButton button = new BoardButton(this, i, j, color, s ,piece);

                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    System.out.println("Clicked piece at " + button.getXPos() + " " + button.getYPos());
                    Position currentPosition = new Position(finalI, finalJ);
                    if (selectedPosition != null && highlightedSpaces.contains(currentPosition)) {
                        Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                        //TODO: move piece and remove other piece if necessary
                        currentPiece.move(board, selectedPosition, currentPosition);
                        BoardButton oldButton = boardButtons[selectedPosition.row][selectedPosition.col];
                        oldButton.setNewIcon(null);
//                        oldButton.validate();
                        oldButton.updateUI();
                        button.setNewIcon(currentPiece);

                        unhighlightSpaces();

                    } else {
                        selectedPosition = new Position(finalI, finalJ);
                        if (board.getSpace(selectedPosition) != null) {
                            Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                            if (currentPiece != null) {
                                highlightSpaces(currentPiece.getAvailableMoves(board, finalI, finalJ));
                            } else {
                                unhighlightSpaces();
                            }
                        } else {
                            unhighlightSpaces();
                        }
                    }

                });

                add(button);
                boardButtons[i][j] = button;
                //TODO: Set the icon of the boardButtons to whatever piece is on it. Every time a piece is moved make sure to change the icons.
            }
        }
    }


    public void highlightSpaces(HashSet<Position> spacesToHighlight) {
        highlightedSpaces = spacesToHighlight;
        for (Position p : highlightedSpaces) {
            boardButtons[p.row][p.col].setHighlight(true);
        }
    }

    public void unhighlightSpaces() {
        for (Position p : highlightedSpaces) {
            boardButtons[p.row][p.col].setHighlight(false);
        }
        highlightedSpaces.clear();
    }

    public BoardButton getButton(int x, int y) {
        return boardButtons[x][y];
    }
}
