public class Bishop extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

    public Bishop(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public Position[] getAvailableMoves(int row, int col) {
        return new Position[0];
    }

    public void move(Space a[][], int currentRow, int currentCol, int newRow, int newCol)
    //Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
    {
        if(legalMove(a, newRow, newCol))
        {
            if(Math.abs(newRow-currentRow)==Math.abs(newCol-currentCol))
            {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);
            }
            else
                System.out.println("Illegal move, please try another one.");
        }
        else
            System.out.println("Illegal move, please try another one.");
    }

    public int getvalue() {
        return value;
    }

    public String getColor(){return color;}

    @Override
    public ChessPieceType getType() {
        return chessPieceType;
    }
}
