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
    abstract Position[] getAvailableMoves(int row, int col);

    /**
     * Tells the piece to attempt to move from a[currentRow][currentCol] to a[newRow][newCol].
     *
     * @param a          instance of the board as a Space[][] (a for "array")
     * @param currentRow row where the piece currently is located.
     * @param currentCol column where the piece currently is location.
     * @param newRow     row where the piece is attempting to move to.
     * @param newCol     column where the piece is attempting to move to.
     * @return A Status indicating whether the move was successful, or not.
     */
    abstract Status move(Space a[][], int currentRow, int currentCol, int newRow, int newCol);

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

    static boolean isASpace(Space[][] a, int row, int col) {
        return a[row][col] != null;
    }

    static boolean isASpace(Board b, Position p){
        return b.getSpace(p) != null;
    }

    static boolean hasAPiece(Space[][] a, int row, int col) {
        return a[row][col].getpiece() != null;
    }

    static boolean hasAPiece(Board b, Position p){
        return b.getSpace(p).getpiece() != null;
    }

    boolean colorsAreTheSame(Space[][] a, int row, int col) {
        return getColor().equals(a[row][col].getpiece().getColor());
    }

}
