// Class to create a chess board for RandomChess program.

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
