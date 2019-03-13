// Class to create a chess board for RandomChess program.

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * Stores a chess board as an object, and provides helper methods.
 * Mostly implies the Space[][] array.
 * <p>
 * <p>
 * If the space is null, there is no space there
 * If piece is null- no piece on the space
 * If piece has(name of pieces)- that piece is on the space
 */
public class Board {

    private Space[][] b;// 2D array that represents board.
    private int rows;
    private int cols;

    /**
     * Default constructor. Makes a normal 8x8 board.
     */
    public Board() {
        this(8, 8);
    }

    /**
     * Makes a square board
     *
     * @param size number of rows and columns that the board will have (# rows = # cols)
     */
    public Board(int size) {
        this(size, size);
    }

    public Board(int rows, int cols) {
        this(rows, cols, false);
    }

    public Board(int rows, int cols, boolean defaultNulls) {
        b = new Space[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                b[i][j] = defaultNulls ? null : new Space();
            }
        }

        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Copy constructor
     *
     * @param other Board from which to copy data from
     */
    public Board(Board other) {
        rows = other.rows;
        cols = other.cols;
        b = new Space[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Space oldSpace = other.getBoard()[i][j];
                Space newSpace = null;
                if (oldSpace != null) {
                    newSpace = new Space();

                    Piece oldPiece = oldSpace.getPiece();
                    Piece newPiece = null;

                    if (oldPiece != null) {
                        try {
                            newPiece = oldSpace.getPiece()
                                    .getClass()
                                    .getConstructor(String.class)
                                    .newInstance(oldSpace.getPiece().getColor());
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }

                    newSpace.setPiece(newPiece);
                }
                b[i][j] = newSpace;
            }
        }
    }

    /**
     * Returns the explicit array that Board implies.
     *
     * @return Space[][] of all the space that the board contains
     */
    public Space[][] getBoard() {
        return b;
    }

    /**
     * Returns the Space at the given row, col coordinates.
     *
     * @param row
     * @param col
     * @return Space at the indicated coordinates in this board.
     */
    public Space getSpace(int row, int col) {
        return b[row][col];
    }

    public Space getSpace(Position position) {
        return b[position.row][position.col];
    }

    public void setSpace(Space sp, int row, int col) {
        b[row][col] = sp;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean positionIsWithinBounds(Position position) {
        return (position.row >= 0 && position.row < rows) && (position.col >= 0 && position.col < cols);
    }

    /**
     * kingInCheck method returns true if the opposite king's position
     * is in the possible moves of a piece on the current board
     */
    public boolean kingInCheck(String color) {
        boolean foundKing = false;
        boolean checkKing = false;
        Position kingPosition = new Position(-1, -1);

        // find kings position to compare to possible moves
        for (int i = 0; i < getRows() && !foundKing; i++) {
            for (int j = 0; j < getCols() && !foundKing; j++) {
                Space pieceSpace = getSpace(i, j);

                if ((pieceSpace.getPiece() != null) && (pieceSpace.getPiece().getType() == Piece.ChessPieceType.KING)
                        && pieceSpace.getPiece().getColor().equals(color)) {
                    kingPosition = new Position(i, j);
                    foundKing = true;
                }
            }
        }

        //then takes the set of all possible moves and sees if they contain the kings position
        for (int a = 0; a < getRows(); a++) {
            for (int b = 0; b < getCols(); b++) {
                Space space = getSpace(a, b);
                if (space.getPiece() != null) {
                    HashSet<Position> moves = space.getPiece().getAvailableMoves(this, a, b);
                    // Iterator<Position> it = moves.iterator();
                    if (moves.contains(kingPosition) && !(space.getPiece().getColor().equals(color))) {
                        checkKing = true;
                        break;
                    }
                }
            }
        }

        return checkKing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return rows == board.rows &&
                cols == board.cols &&
                Arrays.equals(b, board.b);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.hashCode(b);
        return result;
    }
}
