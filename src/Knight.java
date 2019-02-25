public class Knight extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KNIGHT;

    public Knight(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public Position[] getAvailableMoves(int row, int col) {
        return new Position[0];
    }

    @Override
    public void move(Space[][] a, int currentRow, int currentCol, int newRow, int newCol) {
        if(legalMove(a, newRow, newCol))
        {
            if((((newRow==currentRow+2)||(newRow==currentRow-2))&&((newCol==currentCol+1)||(newCol==currentCol-1)))||((newCol==currentCol+2||(newCol==currentCol-2))&&((newRow==currentRow+1)||(newRow==currentRow-1))))
            {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);
            }
            else
                System.out.println("Illegal move, please try another one.");
        }
        else
            System.out.println("Illegal move(According to legalMove method in piece class), please try another one.");
    }

    @Override
    public int getvalue() {
        return value;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ChessPieceType getType() {
        return chessPieceType;
    }
}
