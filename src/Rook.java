public class Rook implements Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.ROOK;

    public Rook(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public Position[] getAvailableMoves(int row, int col) {
        return new Position[0];
    }

    @Override
    public void move(Space[][] a, int currentRow, int currentCol, int newRow, int newCol) {
        if(legalmove(a, currentRow, currentCol, newRow, newCol))
        {
            if(currentRow == newRow || currentCol == newCol)
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
