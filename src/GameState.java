import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description: Keeps track on information during a game, such as whose turn it is.
 */
public class GameState {
    //Can be expanded to accommodate more than two players.
    public static final HashMap<String, Integer> turnValueMap = new HashMap<String, Integer>(){{
        put("white", 0);
        put("black", 1);
    }};

    int turn;
    Board board;
    HashMap<String, ArrayList<Space>> taken;    //Stores the taken pieces.

    public GameState(String turn, Board board){
        this.turn = turnValueMap.get(turn);
        this.board = board;
    }

    public GameState(int turn, Board board) {
        this.turn = turn;
        this.board = board;
    }
}
