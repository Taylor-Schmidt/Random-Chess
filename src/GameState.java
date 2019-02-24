import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description: Keeps track on information during a game, such as whose turn it is.
 */
public class GameState {
    String turnColor;
    Board board;
    Position lastMove;
    HashMap<String, ArrayList<Piece>> taken;    //Stores the taken pieces by their color.

    public GameState(String turn, Board board, Position lastMove){
        this.turnColor = turn;
        this.board = board;
        this.lastMove = lastMove;
    }
}
