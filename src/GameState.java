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
    //Start of my game loop, returns a boolean if the game is still running(in terms of in a game state)
    public boolean gameLoop() {

        /*
        Ryan's Basic Turn logic:
        Display color's turn and board
        Asks the user for input
        (already in game manager)
        do while not in checkmate:

        Ends game when checkmate is true
         */
        return true; //temp return
    }

}
