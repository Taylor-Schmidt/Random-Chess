import java.util.HashSet;

public class Bishop extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

    public Bishop(String c, int v) {
        color = c;
        value = v;
    }


    @Override
    public HashSet<Position> getAvailableMoves(int row, int col) {
        return new HashSet<>();
    }

    public Status move(Space a[][], int currentRow, int currentCol, int newRow, int newCol)
    //Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
    {
        if (legalMove(a, newRow, newCol)) {
            if (Math.abs(newRow - currentRow) == Math.abs(newCol - currentCol)) {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);

                return Status.SucessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
            } else
                return Status.FailedMove();
        } else
            return Status.FailedMove();
    }

    public int getvalue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    @Override
    public ChessPieceType getType() {
        return chessPieceType;
    }
}
