import java.util.*;

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

    public void ChangeTurn()
    {
        if(turnColor=="black")
            turnColor="white";
        else
            turnColor="black";
    }

    public String getTurnColor()
    {
        return turnColor;
    }

    /*
        KingInCheck method returns true if the opposite king's position
        is in the possible moves of a piece on the current board

     */
    public boolean KingInCheck (String color)
    {
        boolean foundKing = false;
        boolean checkKing = false;
//        Space kingSpace = null;
//        String kingColor = "";
        Position kingPosition = new Position(-1, -1);

        // find kings position to compare to possible moves
//        while(!foundKing) {
            for (int i = 0; i < board.getRows() && !foundKing; i++) {
                for (int j = 0; j < board.getCols() && !foundKing; j++) {
                    Space pieceSpace = board.getSpace(i, j);

                    if ((pieceSpace.getPiece() != null) && (pieceSpace.getPiece().getType() == Piece.ChessPieceType.KING)
                    && pieceSpace.getPiece().getColor().equals(color)) {
//                        kingSpace = pieceSpace;
                        kingPosition = new Position(i, j);
//                        kingColor = pieceSpace.getPiece().getColor();
                        foundKing = true;
                    }
                }
            }
//        }

        //then takes the set of all possible moves and sees if they contain the kings position
        for (int a = 0; a < board.getRows(); a++){
            for (int b = 0; b < board.getCols(); b++){
                Space space = board.getSpace(a,b);
                if (space.getPiece() != null  ){
                    HashSet<Position> moves = space.getPiece().getAvailableMoves(board, a, b);
                   // Iterator<Position> it = moves.iterator();
                    if(moves.contains(kingPosition) && !(space.getPiece().getColor().equals(color)))
                    {
                        checkKing = true;
                        break;
                    }
                }
            }
        }
        //comment

        return checkKing;
    }
}
