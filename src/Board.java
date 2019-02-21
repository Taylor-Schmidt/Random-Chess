// Class to create a chess board for RandomChess program.
 /**
     if the space is null, there is no space there
     if piece is null- no piece on the space
     if piece has(name of pieces)- that piece is on the space
    */
public class Board {

    private Space[][] b;// 2D array that represents board.
    private int xSize;
    private int ySize;

    /**
     * Default constructor. Makes a normal 8x8 board.
     */
    public Board() {
        this(8, 8);
    }

    /**
     * Makes a square board
     * @param size
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
    }

    public Space[][] getBoard() {
        return b;
    }

    public Space getSpace(int x, int y) {
        return b[x][y];
    }

    public void setSpace(Space sp, int x, int y)
    {
        b[x][y]=sp;
    }
}
