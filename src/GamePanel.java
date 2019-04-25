import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
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

    private static final String fileName = "save.dat";



    GamePanel() {
        this(true);
    }

    GamePanel(boolean isNewGame) {
        super();

        setBackground(ColorGenerator.backgroundColor);
        setLayout(new GridBagLayout());
        setUpBoard(isNewGame);
    }

    @SuppressWarnings("unchecked")
    void setUpBoard(boolean isNewGame) {

        if (isNewGame) {
            //Creates BoardPanel
            board = new Board(true);

            //Sets pieces on board.
            setPieces();
        } else {
//            board = loadGame();

            board = new Board(true);
            
            setPieces();
        }

        if (boardPanel != null) {
            remove(boardPanel);
        }

        boardPanel = new BoardPanel(board.getRows(), board.getCols(), board);
        boardPanel.addNewGameListener(e -> newGame());
        boardPanel.addChangeTurnListener(e -> changeTurn());
        boardPanel.addSaveListener(e -> {
            try {
                ArrayList<GameState> gameStates = (ArrayList<GameState>) boardPanel.getClientProperty(BoardPanel.STATES);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
                oos.writeObject(gameStates);
                oos.flush();
                oos.close();
            } catch (ClassCastException| IOException exception) {
                System.out.println("Could not save");
            }
        });

        add(boardPanel, gc);
    }

    void newGame() {
        setUpBoard(true);
    }

    void loadGame() {

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
