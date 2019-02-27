import java.awt.*;
import java.util.Objects;

/**
 * Stores the value for a row and col.
 * Primarily used to get both values from a method, since Java can return only one object;
 */
public class Position {
    int row, col;



    /**
     * Basic constructor, exposes row and col values
     * @param row Row location of some object relative to an instance of board.
     * @param col Column location of some object relative to an instance of board.
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Copy constructor
     * @param position Position instance from which to copy values.
     */
    public Position(Position position){
        row = position.row;
        col = position.col;
    }

    /**
     * Converts row and column coordinates to standard chess coordinates.
     * Example:
     *      [2][3] -> D6
     *
     * For reference, here is a standard chess board labeled with chess coordinates:
     *    A	  B	  C	  D	  E	  F	  G	  H
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 8| ♜	| ♞	| ♝	| ♛	| ♚	| ♝	| ♞	| ♜	|	8
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 7| ♟	| ♟	| ♟	| ♟	| ♟	| ♟	| ♟	| ♟	|	7
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 6| 	| 	| 	| 	| 	| 	| 	| 	|	6
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 5| 	| 	| 	| 	| 	| 	| 	| 	|	5
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 4| 	| 	| 	| 	| 	| 	| 	| 	|	4
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 3| 	| 	| 	| 	| 	| 	| 	| 	|	3
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 2| ♙	| ♙	| ♙	| ♙	| ♙	| ♙	| ♙	| ♙	|	2
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 1| ♖	| ♘	| ♗	| ♕	| ♔	| ♗	| ♘	| ♖	|	1
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 	  A	  B	  C	  D	  E	  F	  G	  H
     *
     * 	  And here is the board labeled with row and column coordinates:
     * 	 0	  1	  2	  3	  4	  5	  6	  7
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 0| ♜	| ♞	| ♝	| ♛	| ♚	| ♝	| ♞	| ♜	|	0
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 1| ♟	| ♟	| ♟	| ♟	| ♟	| ♟	| ♟	| ♟	|	1
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 2| 	| 	| 	| 	| 	| 	| 	| 	|	2
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 3| 	| 	| 	| 	| 	| 	| 	| 	|	3
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 4| 	| 	| 	| 	| 	| 	| 	| 	|	4
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 5| 	| 	| 	| 	| 	| 	| 	| 	|	5
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 6| ♙	| ♙	| ♙	| ♙	| ♙	| ♙	| ♙	| ♙	|	6
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 7| ♖	| ♘	| ♗	| ♕	| ♔	| ♗	| ♘	| ♖	|	7
     * 	+--	+--	+--	+--	+--	+--	+--	+--	+
     * 	 0	  1	  2	  3	  4	  5	  6	  7
     *
     *  Note:
     *  Chess coordinates are in the form A1, where A is the row by letter, starting at A, and 1 is the column by
     *  integer, starting at 1. Java array coordinates, or "Row and column coordinates", are in the form [0][0],
     * 	where both the row and column are indicated by an integer and start at 0.
     *
     * @param row A row number, specifically in reference to the row index of an object in a Board array.
     * @param col A column number, specifically in reference to the row index of an object in a Board array.
     * @return A string containing the standard chess notation of the given coordinate.
     */
    public static String parsePosition(int row, int col) {
        char letter = (char) ('A' + col);

        return letter + "" + (8 - row);
    }

    /**
     * Shadows parsePosition(int row, int col) with Position class functionality.
     * @param position Position to convert to standard Chess coordinates.
     * @return String containing a set of standard Chess coordinates.
     */
    public static String parsePosition(Position position){
        return parsePosition(position.row, position.col);
    }

    /**
     * Converts standard chess coordinates to "row and column" array coordinates.
     * Example:
     *      E3 -> [4][5]
     *
     *  See the comment for String parsePosition(int row, int col) above.
     *
     * @param s String containing a position in standard chess coordinates.
     * @return a Position instance containing the row and column values of the same position.
     */
    public static Position parsePosition(String s) {
        s = s.trim().toUpperCase();
//        System.out.println("'" + s + "'");
        if (s.length() > 2) {
            return null;
        } else {

            int col = s.charAt(0) - 'A';
            int row = 7 - (s.charAt(1) - '1');

            return new Position(row, col);
        }
    }

    @Override
    public String toString() {
        return "[" + row + "][" + col + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    final static Position i = new Position(1, 0);
    final static Position j = new Position(0, 1);

    public Position scalarMult(int scalar){
        return new Position(row * scalar, col * scalar);
    }
}
