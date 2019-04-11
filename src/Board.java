import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

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
//    private boolean[][] spaceNulls;

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
                    newSpace = new Space(true);

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

    public Board(boolean isRandom){
        this(16, isRandom);
    }

    //WARNING! DOES NOT WORK WITH SIZES OTHER THAN 16x16!
    //TODO: Make this solution more general and then make it public
    //TODO: Make this constructor fall through to a default when isRandom = false
    private Board(int size, boolean isRandom) {
        if (isRandom) {
//            spaceNulls = new boolean[size][size];
            b = new Space[size][size];
            rows = size;
            cols = size;
            Random randomNum = new Random();
            int row;
            int column;

            for (row = 4; row < 7; row++) {
                for (column = 4; column <= 11; column++) {
                    b[row][column] = new Space();
                }
            }
            for (row = 9; row < 12; row++) {
                for (column = 4; column <= 11; column++) {
                    b[row][column] = new Space();
                }
            }

            //Randomize middle part of board
            int i = 0;
            if (8 > randomNum.nextInt(10)) {
                createTile(4, 7, 1, 2);
                createTile(11, 7, 1, 2);
                i++;
            }
            if (8 > randomNum.nextInt(10)) {
                createTile(5, 7, 1, 2);
                createTile(10, 7, 1, 2);
                i++;
            }
            if (8 > randomNum.nextInt(10)) {
                createTile(6, 7, 1, 2);
                createTile(9, 7, 1, 2);
                i++;
            }
            if (8 > randomNum.nextInt(10)) {
                createTile(7, 7, 1, 2);
                createTile(8, 7, 1, 2);
                i++;
            }
            if (i == 0) {
                createTile(6, 7, 1, 2);
                createTile(9, 7, 1, 2);
                createTile(7, 7, 1, 2);
                createTile(8, 7, 1, 2);
            }
            //randomizing corner parts
            int rand = randomNum.nextInt(3);
            if (0 == rand) {
                createTile(1, 1, 4, 6);
                createTile(11, 1, 4, 6);
                createTile(1, 9, 4, 6);
                createTile(11, 9, 4, 6);
            } else if (1 == rand) {
                createTile(2, 2, 3, 5);
                createTile(11, 2, 3, 5);
                createTile(2, 9, 3, 5);
                createTile(11, 9, 3, 5);
            } else {
                createTile(5, 3, 2, 4);
                createTile(9, 3, 2, 4);
                createTile(5, 9, 2, 4);
                createTile(9, 9, 2, 4);
            }
            //randomizing side parts
            int rand2 = randomNum.nextInt(4);
            if (rand2 == 0) {
                createTile(1, 6, 3, 4);
                createTile(12, 6, 3, 4);
            }
            if (rand2 == 1 || rand2 == 2) {
                createTile(2, 6, 2, 4);
                createTile(12, 6, 2, 4);
            }
            if (rand2 == 2 || rand2 == 1 || rand == 2) {
                createTile(3, 6, 1, 4);
                createTile(12, 6, 1, 4);
            }
            if (0 == randomNum.nextInt(2)) {
                createTile(6, 12, 4, 2);
                createTile(6, 2, 4, 2);
            }
            if (0 == randomNum.nextInt(2)) {
                createTile(6, 12, 4, 1);
                createTile(6, 3, 4, 1);
            }
            if (0 == randomNum.nextInt(2)) {
                createTile(4, 3, 7, 1);
                createTile(4, 12, 7, 1);
            }
        }
    }

    private void createTile(int startColumn, int startRow, int width, int height) {
        for (int m_startRow = startRow; m_startRow < height + startRow; m_startRow++)
            for (int m_startColumn = startColumn; m_startColumn < width + startColumn; m_startColumn++) {
                b[m_startRow][m_startColumn] = new Space();
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
                if (pieceSpace != null) {
                    if ((pieceSpace.getPiece() != null) && (pieceSpace.getPiece().getType() == Piece.ChessPieceType.KING)
                            && pieceSpace.getPiece().getColor().equals(color)) {
                        kingPosition = new Position(i, j);
                        foundKing = true;
                    }
                }
            }
        }

        //then takes the set of all possible moves and sees if they contain the kings position
        for (int a = 0; a < getRows() && !checkKing; a++) {
            for (int b = 0; b < getCols() && !checkKing; b++) {
                Position p = new Position(a, b);
                if (Piece.isASpace(this, p)) {
                    Space space = getSpace(p);
                    if (Piece.hasAPiece(this, p)) {
                        if (!space.getPiece().getColor().equals(color)) {
                            HashSet<Position> moves = space.getPiece().getAvailableMoves(this, p);
                            if (moves.contains(kingPosition)) {
                                checkKing = true;
                            }
                        }
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
                Arrays.deepEquals(b, board.b);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.hashCode(b);
        return result;
    }

    public void pawnToQueen(String color, int row, int col){
        Position position;
        boolean topOrBottom = true;
        for(int i = 1; i<getRows(); i++){
            if(color.equals("white")){
                position = new Position(row-i,col);
                if(positionIsWithinBounds(position) && getSpace(position)!=null) {
                    topOrBottom = false;
                }
            }
            else{
                position = new Position(row+i,col);
                if(positionIsWithinBounds(position) && getSpace(position)!=null) {
                    topOrBottom = false;
                }
            }

        }
        if(topOrBottom){
            setSpace(new Space(new Queen(color)), row, col);
        }
    }
}
