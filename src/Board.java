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

    public Space[][] getBoard()
            //returns the entire board.
    {
        return b;
    }

    public Space getSpace(int x, int y)
            //returns the space of the arguments you give it.
    {
        return b[x][y];
    }

    public void DisplayBoard()
    // Outputs text version of board.
    {
        for(int i=7; i>=0; i--)
        {
            for(int j=0; j<8; j++)
            {
                if(b[i][j]==null)
                {
                    System.out.print(0 + " ");
                }
                else
                {
                    System.out.print(b[i][j].getpiece().getvalue() + " ");
                }
            }
            System.out.println();
        }
    }

}
