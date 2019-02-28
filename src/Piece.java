import java.util.HashSet;

/**
 * All chess pieces implement this interface.
 * Provides methods to make their usage more consistent across the board.
 */
public abstract class Piece {

    /**
     * Used to identify what kind of piece it is (for displaying the piece, for example)
     * Possibly could be replaced with HashMap of String values, or a set of instanceof calls.
     */
    enum ChessPieceType {
        PAWN, BISHOP, KING, QUEEN, ROOK, KNIGHT
    }

    /**
     * Returns the set of all possible, legal Positions that a piece at the given row and col can go to.
     *
     * @param row row of the piece in the instance of Board
     * @param col column of the piece in the instance of Board
     * @return set of Positions that the piece is able to move to
     */
    abstract HashSet<Position> getAvailableMoves(Board board, int row, int col);

    public HashSet<Position> getAvailableDiagonalMoves(Board board, int row, int col) {
        HashSet<Position> availablePositions = new HashSet<>();

        probeByDirectionVector(board, row, col, new Position(1, 1), availablePositions);

        probeByDirectionVector(board, row, col, new Position(-1, 1), availablePositions);

        probeByDirectionVector(board, row, col, new Position(1, -1), availablePositions);

        probeByDirectionVector(board, row, col, new Position(-1, -1), availablePositions);

        return availablePositions;
    }

    public HashSet<Position> getAvailableHorizontalVerticalMoves(Board board, int row, int col){
        HashSet<Position> availablePositions = new HashSet<>();

        probeByDirectionVector(board, row, col, Position.i, availablePositions);

        probeByDirectionVector(board, row, col, Position.i.scalarMult(-1), availablePositions);

        probeByDirectionVector(board, row, col, Position.j, availablePositions);

        probeByDirectionVector(board, row, col, Position.j.scalarMult(-1), availablePositions);

        return availablePositions;
    }

    public static void main(String[] args) {
        King king = new King("white", 0);
        Board board = new Board();
        System.out.println(king.getAvailableMoves(board, 3, 3));
    }

    /**
     * Uses a 2x1 direction vector (an instance of Position class) and probes in that direction by scaling in that
     * direction by scalar multiples of the vector.
     * @param board
     * @param row
     * @param col
     * @param directionVector
     * @param availablePositions
     */
     void probeByDirectionVector(Board board, int row, int col, Position directionVector, HashSet<Position> availablePositions){
        probeByDirectionVector(board, row, col, directionVector, availablePositions, board.getRows(), board.getCols());
    }

    void probeByDirectionVector(Board board, int row, int col, Position directionVector,
                                HashSet<Position> availablePositions, int maxRowScale, int maxColScale){
        boolean encounteredIllegalMove = false;
        for (int i = 1, j = 1; 0 <= row + (directionVector.row * i) && row + (directionVector.row * i) < board.getRows()
                && 0 <= col + (directionVector.col * j) && col + (directionVector.col * j) < board.getCols() &&
                 i <= maxRowScale && j <= maxColScale && !encounteredIllegalMove; i++, j++){

            Position newPosition = new Position(row + (directionVector.row * i), col + (directionVector.col * j) );

            if (isASpace(board, newPosition)) {
                if (hasAPiece(board, newPosition)) {
                    encounteredIllegalMove = true;
                    if (!colorsAreTheSame(board, newPosition)){
                        availablePositions.add(newPosition);
                    }
                }
            } else {
                encounteredIllegalMove = true;
            }
//            encounteredIllegalMove = !legalMove(board, newPosition);

            if (!encounteredIllegalMove){
                availablePositions.add(newPosition);
            }
        }
    }


    /**
     * Tells the piece to attempt to move from a[currentRow][currentCol] to a[newRow][newCol].
     *
     * @param board         instance of the board
     * @param currentRow row where the piece currently is located.
     * @param currentCol column where the piece currently is location.
     * @param newRow     row where the piece is attempting to move to.
     * @param newCol     column where the piece is attempting to move to.
     * @return A Status indicating whether the move was successful, or not.
     */
     Status move(Board board, int currentRow, int currentCol, int newRow, int newCol){
         if (getAvailableMoves(board, currentRow, currentCol).contains(new Position(newRow, newCol))) {
             board.getSpace(newRow, newCol).setPiece(this);
             board.getSpace(currentRow, currentCol).setPiece(null);
             return Status.SuccessfulMove(getType(), currentRow, currentCol, newRow, newCol);
         } else {
             return Status.FailedMove();
         }
     }

    /**
     * The AI is expected to make decisions based on the idea that different pieces are of different worth.
     * The value that this method returns determines the Piece's worth to the AI.
     */
    abstract int getvalue();

    /**
     * Pieces are associated with a color (in a standard game, black and white). This returns the color associated with
     * this Piece.
     *
     * @return String representing color.
     */
    abstract String getColor();

    /**
     * Identifies what kind of piece this is (King, Queen, Knight, etc.)
     *
     * @return type as a ChessPieceType (from Piece.ChessPieceType enum)
     */
    abstract ChessPieceType getType();

    /**
     * Helper method which indicates whether the given space is withing the bounds of the board and does not contain a
     * piece of the same color as this one.
     *
     * @param a      "Board" (as Space[][]) in which to perform this check
     * @param newRow Row of the space upon which to perform this check
     * @param newCol Column of the space upon which to perform this check
     * @return true if this (Piece) is able to be moved to this location. Else false.
     */
    boolean legalMove(Space[][] a, int newRow, int newCol) {
        if (isASpace(a, newRow, newCol)) {
            return (!hasAPiece(a, newRow, newCol) || !colorsAreTheSame(a, newRow, newCol));
        }

        return false;
    }

    boolean legalMove(Board board, Position position){
        return legalMove(board.getBoard(), position.row, position.col);
    }

    boolean legalMove(Board board, int row, int col){
        return legalMove(board.getBoard(), row, col);
    }

    static boolean isASpace(Space[][] a, int row, int col) {
        return a[row][col] != null;
    }

    static boolean isASpace(Board b, Position p){
        return b.getSpace(p) != null;
    }

    static boolean hasAPiece(Space[][] a, int row, int col) {
        return a[row][col].getPiece() != null;
    }

    static boolean hasAPiece(Board b, Position p){
        return b.getSpace(p).getPiece() != null;
    }

    boolean colorsAreTheSame(Space[][] a, int row, int col) {
        return getColor().equals(a[row][col].getPiece().getColor());
    }

    boolean colorsAreTheSame(Board board, Position position){
        return getColor().equals(board.getSpace(position).getPiece().getColor());
    }

}
