import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the panel that the game will take place on.
 */

class GamePanel extends JPanel {
    private BoardPanel boardPanel;
    private Board board;

    @SuppressWarnings("FieldCanBeLocal")
    private static String black = "black";
    private static String white = "white";

    private GridBagConstraints gc = new GridBagConstraints();

    private ArrayList<ActionListener> turnChangeListeners = new ArrayList<>();

    GamePanel() {
        super();
        setBackground(ColorGenerator.backgroundColor);
        setLayout(new GridBagLayout());

        newGame();
    }

    void newGame() {
        //Creates BoardPanel
        board = new Board(true);

        //Sets pieces on board.
        setPieces();

        if (boardPanel != null) {
            remove(boardPanel);
        }

        boardPanel = new BoardPanel(board.getRows(), board.getCols(), board);
        boardPanel.addNewGameListener(e -> newGame());

        boardPanel.addChangeTurnListener(e -> changeTurn());

        add(boardPanel, gc);
    }

    private void changeTurn() {
        callListeners(turnChangeListeners);
    }

    private void callListeners(ArrayList<ActionListener> listeners) {
        for (ActionListener listener : listeners) {
            listener.actionPerformed(null);
        }
    }

    void addTurnChangeListener(ActionListener e) {
        turnChangeListeners.add(e);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    private void setPiecesTest() {
        board.getSpace(new Position(Position.parsePosition("H8"))).setPiece(new King(black));
        board.getSpace(new Position(Position.parsePosition("F7"))).setPiece(new King(white));
        board.getSpace(new Position(Position.parsePosition("f6"))).setPiece(new Queen(white));
    }

    //Specifically made for a 16x16 board.
    private void setPieces() {
        initPiecesForColor(white, 11, 10);
        initPiecesForColor(black, 4, 5);
    }

    private void initPiecesForColor(String color, int royalRow, int pawnRow) {
        board.setSpace(new Space(new Rook(color)), royalRow, 4);
        board.setSpace(new Space(new Knight(color)), royalRow, 5);
        board.setSpace(new Space(new Bishop(color)), royalRow, 6);
        board.setSpace(new Space(new Queen(color)), royalRow, 7);
        board.setSpace(new Space(new King(color)), royalRow, 8);
        board.setSpace(new Space(new Bishop(color)), royalRow, 9);
        board.setSpace(new Space(new Knight(color)), royalRow, 10);
        board.setSpace(new Space(new Rook(color)), royalRow, 11);
        for (int i = 4; i < 12; i++) {
            board.setSpace(new Space(new Pawn(color)), pawnRow, i);
        }
    }
}
