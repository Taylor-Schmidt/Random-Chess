import java.util.*;

/**
 * Keeps track on information during a game, such as the color of whose turn it is, the locations of all pieces on the
 * board (stored in the Board class), the position of the last move, and a map of all the takenPieces pieces, organized
 * by the color of the piece.
 * <p>
 * Used to check the pieces of information listed above. By storing GameStates, it is possible to obtain that
 * information regarding the current or previous "states" of the game. This makes it very easy to create things such as
 * an "undo" function.
 */
public class GameState {
    private String turnColor; //Color of the current player's turn at the time of creation of this state.
    private Board board; //
    private Position lastMove;
    private HashMap<String, ArrayList<Piece>> takenPieces = new HashMap<>();    //Stores the takenPieces pieces by their color.

    /**
     * Default constructor. Warning! Objects created with this constructor must have values initialized in some other
     * way.
     */
    public GameState() {

    }

    /**
     * Constructor, initializing some basic values
     *
     * @param turn     Color of initial turn in the game.
     * @param board    Board consisting of all spaces and their pieces.
     * @param lastMove Final position of last successfully moved piece.
     */
    public GameState(String turn, Board board, Position lastMove) {
        this.turnColor = turn;
        this.board = board;
        this.lastMove = lastMove;
    }

    public GameState(GameState gameState) {
        turnColor = gameState.turnColor;
        board = new Board(gameState.board);
        lastMove = gameState.lastMove != null? new Position(gameState.lastMove) : null;
        takenPieces = gameState.takenPieces;
    }

    public void changeTurn() {
        if (turnColor.equals("black")) {
            turnColor = "white";
        } else {
            turnColor = "black";
        }
    }

    public String getTurnColor() {
        return turnColor;
    }

    public Position getLastMove() {
        return lastMove;
    }

    public void setLastMove(Position lastMove) {
        this.lastMove = lastMove;
    }

    public Board getBoard() {
        return board;
    }

    public HashMap<String, ArrayList<Piece>> getTakenPieces() {
        return takenPieces;
    }

    public ArrayList<Piece> getTakenPieces(String color) {
        return takenPieces.get(color);
    }

    public void addTakenPiece(Piece piece) {
        if (!takenPieces.containsKey(piece.getColor())){
            takenPieces.put(piece.getColor(), new ArrayList<>());
        }
        takenPieces.get(piece.getColor()).add(piece);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameState)) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(turnColor, gameState.turnColor) &&
                Objects.equals(board, gameState.board) &&
                Objects.equals(lastMove, gameState.lastMove) &&
                Objects.equals(takenPieces, gameState.takenPieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnColor, board, lastMove, takenPieces);
    }
}
