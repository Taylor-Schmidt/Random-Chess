// Class to create a chess board for RandomChess program.

public class Board {

    private Space[][] b;     // 2D array that represents board.

    public Board()
    // Default constructor. Makes normal 8x8 board.
    {
        b= new Space[8][8];
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                b[i][j]=new Space();
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
