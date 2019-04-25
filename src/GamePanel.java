import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * This is the panel that the game will take place on.
 */
@SuppressWarnings("FieldCanBeLocal")
class GamePanel extends JPanel {
    private BoardPanel boardPanel;
    private static String black = "black";
    private static String white = "white";

    private static final String fileName = "save.dat";

    private GridBagConstraints gc = new GridBagConstraints();

    private ArrayList<ActionListener> turnChangeListeners = new ArrayList<>();


    GamePanel() {
        setBackground(ColorGenerator.backgroundColor);
        setLayout(new GridBagLayout());
    }

    @SuppressWarnings("unchecked")
    void setUpBoard(boolean isNewGame) {

        if (boardPanel != null) {
            remove(boardPanel);
        }

        if (isNewGame) {
            newGame();
        } else {

            ArrayList<GameState> gameStates = loadGame();

            if (gameStates == null) {
                newGame();
            } else {
                boardPanel = new BoardPanel(gameStates);
            }
        }


        boardPanel.addNewGameListener(e -> setUpBoard(true));
        boardPanel.addChangeTurnListener(e -> changeTurn());
        boardPanel.addSaveListener(e -> {
            try {
                ArrayList<GameState> gameStates = (ArrayList<GameState>) boardPanel.getClientProperty(BoardPanel.STATES);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
                oos.writeObject(gameStates);
                oos.flush();
                oos.close();
            } catch (ClassCastException | IOException exception) {
                System.out.println("Could not save");
            }
        });

        add(boardPanel, gc);
    }

    void save() {
        if (boardPanel != null) {
            boardPanel.save();
        }
    }

    private void newGame() {
        //Creates BoardPanel
        Board board = new Board(true);

        //Sets pieces on board.
        setPieces(board);

        ArrayList<GameState> gameStates = new ArrayList<>();

        gameStates.add(new GameState("white", board, null));

        boardPanel = new BoardPanel(gameStates);
    }


    @SuppressWarnings("unchecked")
    ArrayList<GameState> loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<GameState> states = (ArrayList<GameState>) ois.readObject();
            ois.close();

            return states;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
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

    //Specifically made for a 16x16 board.
    private void setPieces(Board board) {
        initPiecesForColor(board, white, 11, 10);
        initPiecesForColor(board, black, 4, 5);
    }

    private void initPiecesForColor(Board board, String color, int royalRow, int pawnRow) {
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
