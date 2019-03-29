import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This is the panel that the game will take place on.
 */

public class GamePanel extends JPanel {
    private BoardPanel boardPanel;
    FeedBackPanel feedBackPanel;
    private Board board;
    private ArrayList<GameState> gameStates = new ArrayList<>();

    private static String black = "black";
    private static String white = "white";

    public GamePanel(int w, int h) {
        super();

        setBackground(Color.CYAN);

        //Creates BoardPanel
        board = new Board(true);

        //Sets pieces on board.
        setPieces();

        GameState currentState = new GameState(white, board, null);
        gameStates.add(new GameState(currentState));
        feedBackPanel = new FeedBackPanel();
        boardPanel = new BoardPanel(w, h, this);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        add(boardPanel, gc);
        add(feedBackPanel,gc);
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
        space = new Space(new King("white"));
        board.setSpace(space, 11, 7);
        space = new Space(new Queen("white"));
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

    GameState getCurrentState(){
        return gameStates.get(gameStates.size() - 1);
    }

    Board getBoard(){
        return getCurrentState().getBoard();
    }
}
