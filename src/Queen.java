import java.util.HashSet;

public class Queen extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.QUEEN;

    public Queen(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        return new HashSet<>();
    }

    @Override
    public Status move(Board board, int currentRow, int currentCol, int newRow, int newCol) {
        Space[][] a = board.getBoard();
        if(legalMove(a, newRow, newCol))
        {
            if(currentRow == newRow || currentCol == newCol || Math.abs(newRow-currentRow)==Math.abs(newCol-currentCol))
            {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);
                return Status.SucessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
            }
            else
                return Status.FailedMove();
        }
        else
            return Status.FailedMove();
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
