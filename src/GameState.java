import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Keeps track on information during a game, such as the color of whose turn it is, the locations of all pieces on the
 * board (stored in the Board class), the position of the last move, and a map of all the taken pieces, organized by the
 * color of the piece.
 *
 * Used to check the pieces of information listed above. By storing GameStates, it is possible to obtain that
 * information regarding the current or previous "states" of the game. This makes it very easy to create things such as
 * an "undo" function.
 */
public class GameState {
    String turnColor; //Color of the current player's turn at the time of creation of this state.
    Board board; //
    Position lastMove;
    HashMap<String, ArrayList<Piece>> taken = new HashMap<>();    //Stores the taken pieces by their color.

    /**
     * Default constructor. Warning! Objects created with this constructor must have values initialized in some other
     * way.
     */
    public GameState(){

    }

    /**
     * Constructor, initializing some basic values
     * @param turn
     * @param board
     * @param lastMove
     */
    public GameState(String turn, Board board, Position lastMove){
        this.turnColor = turn;
        this.board = board;
        this.lastMove = lastMove;
    }

    public GameState(GameState gameState){
        this.turnColor = gameState.turnColor;
        board = new Board(gameState.board);
        lastMove = new Position(gameState.lastMove);
    }
}
