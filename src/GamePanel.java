import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the panel that the game will take place on.
 */

class GamePanel extends JPanel {
    private BoardPanel boardPanel;
    FeedBackPanel feedBackPanel;
    private Board board;
    private ArrayList<GameState> gameStates = new ArrayList<>();

    @SuppressWarnings("FieldCanBeLocal")
    private static String black = "black";
    private static String white = "white";

    private GridBagConstraints gc = new GridBagConstraints();

    private ArrayList<ActionListener> turnChangeListeners = new ArrayList<>();

    GamePanel() {
        super();
        setBackground(ColorGenerator.backgroundColor);
        setLayout(new GridBagLayout());


//        feedBackPanel = new FeedBackPanel();
//        add(feedBackPanel, gc);

        newGame();

    }

    void newGame() {
        //Creates BoardPanel
        board = new Board(true);

//        setPiecesTest();

        //Sets pieces on board.
        setPieces();

        GameState currentState = new GameState(white, board, null);
        gameStates.add(new GameState(currentState));

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

    private void setPieces() {
        //Specifically made for a 16x16 board.
        Space space;
        space = new Space(new Rook("black"));
        board.setSpace(space, 4, 4);
        space = new Space(new Knight("black"));
        board.setSpace(space, 4, 5);
        space = new Space(new Bishop("black"));
        board.setSpace(space, 4, 6);
        space = new Space(new Queen("black"));
        board.setSpace(space, 4, 7);
        space = new Space(new King("black"));
        board.setSpace(space, 4, 8);
        space = new Space(new Bishop("black"));
        board.setSpace(space, 4, 9);
        space = new Space(new Knight("black"));
        board.setSpace(space, 4, 10);
        space = new Space(new Rook("black"));
        board.setSpace(space, 4, 11);
        for (int i = 4; i < 12; i++) {
            space = new Space(new Pawn("black"));
            board.setSpace(space, 5, i);
        }

        space = new Space(new Rook("white"));
        board.setSpace(space, 11, 4);
        space = new Space(new Knight("white"));
        board.setSpace(space, 11, 5);
        space = new Space(new Bishop("white"));
        board.setSpace(space, 11, 6);
        space = new Space(new Queen("white"));
        board.setSpace(space, 11, 7);
        space = new Space(new King("white"));
        board.setSpace(space, 11, 8);
        space = new Space(new Bishop("white"));
        board.setSpace(space, 11, 9);
        space = new Space(new Knight("white"));
        board.setSpace(space, 11, 10);
        space = new Space(new Rook("white"));
        board.setSpace(space, 11, 11);
        for (int i = 4; i < 12; i++) {
            space = new Space(new Pawn("white"));
            board.setSpace(space, 10, i);
        }
    }

    private GameState getCurrentState() {
        return gameStates.get(gameStates.size() - 1);
    }

    Board getBoard() {
        return getCurrentState().getBoard();
    }

}
