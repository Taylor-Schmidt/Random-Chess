// Class to create a chess board for RandomChess program.
 /**
  * Stores a chess board as an object, and provides helper methods.
  * Mostly implies the Space[][] array.
  *
  * if the space is null, there is no space there
  * if piece is null- no piece on the space
  * if piece has(name of pieces)- that piece is on the space
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
     * @param size number of rows and columns that the board will have (# rows = # cols)
     */
    public Board(int size){
        this(size, size);
    }

    public Board(int rows, int cols){
        this(rows, cols, false);
    }

    public Board(int rows, int cols, boolean defaultNulls){
        b = new Space[rows][cols];
        for (int i  = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                b[i][j] = defaultNulls? null : new Space();
            }
        }

        this.rows = rows;
        this.cols = cols;
    }

     /**
      * Copy constructor
      * @param other Board from which to copy data from
      */
    public Board(Board other){
        rows = other.rows;
        cols = other.cols;
        b = new Space[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                b[i][j] = other.getBoard()[i][j];
            }
        }
    }

     /**
      * Returns all the explicit array that Board implies.
      * @return
      */
    public Space[][] getBoard() {
        return b;
    }

     /**
      * Returns the Space at the given row, col coordinates.
      * @param row
      * @param col
      * @return
      */
    public Space getSpace(int row, int col) {
        return b[row][col];
    }

    public void setSpace(Space sp, int row, int col)
    {
        b[row][col]=sp;
    }
}
