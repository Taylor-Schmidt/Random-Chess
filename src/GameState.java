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
    private int fiftyMoveDrawCounter = 0;

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
        lastMove = gameState.lastMove != null ? new Position(gameState.lastMove) : null;
        takenPieces = gameState.takenPieces;
        fiftyMoveDrawCounter = gameState.fiftyMoveDrawCounter;
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
    public void setBoard(Board b){
        board=b;
    }

    public HashMap<String, ArrayList<Piece>> getTakenPieces() {
        return takenPieces;
    }

    public ArrayList<Piece> getTakenPieces(String color) {
        return takenPieces.get(color);
    }

    public void addTakenPiece(Piece piece) {
        if (!takenPieces.containsKey(piece.getColor())) {
            takenPieces.put(piece.getColor(), new ArrayList<>());
        }
        takenPieces.get(piece.getColor()).add(piece);
    }

    public void incrementFiftyMoveDrawCounter() {
        fiftyMoveDrawCounter++;
    }

    public void resetFiftyMoveDrawCounter() {
        fiftyMoveDrawCounter = 0;
    }

    public boolean fiftyMoveDraw() {
        return fiftyMoveDrawCounter == 50;
    }

    public int getFiftyMoveDrawCounter() {
        return fiftyMoveDrawCounter;
    }

    public void setFiftyMoveDrawCounter(int fiftyMoveDrawCounter) {
        this.fiftyMoveDrawCounter = fiftyMoveDrawCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameState)) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(turnColor, gameState.turnColor) &&
                Objects.equals(board, gameState.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnColor, board);
    }


    /**
     * Returns true if the player has a possible move that doesn't put the king in check
     *
     * @param board
     * @return
     */
    public boolean hasAvailableMove(Board board) {
        boolean hasAvailableMove = false;

        //iterate through every space on the board
        for (int i = 0; i < board.getRows() && !hasAvailableMove; i++) {
            for (int j = 0; j < board.getCols() && !hasAvailableMove; j++) {
                Position position = new Position(i, j);
                Space space = board.getSpace(position);

                //Check for a piece at that location
                if (space != null) {
                    Piece piece = space.getPiece();
                    if (piece != null) {
                        if (piece.getColor().equals(turnColor)) {
                            HashSet<Position> moves = piece.getAvailableMoves(board, position);

                            Iterator<Position> movesIterator = moves.iterator();
                            while (movesIterator.hasNext() && !hasAvailableMove) {
                                Position move = movesIterator.next();
                                if (piece.legalMove(board, move)) {
                                    Board tempBoard = new Board(board);
                                    Status status = tempBoard.getSpace(position).getPiece().move(tempBoard, position, move);

                                    hasAvailableMove = !status.status.equals(Status.STATUS_BAD) && !tempBoard.kingInCheck(turnColor);

                                }
                            }
                        }
                    }
                }
            }
        }

        return hasAvailableMove;
    }
}