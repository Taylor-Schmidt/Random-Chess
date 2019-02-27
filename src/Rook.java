import java.util.HashSet;

public class Rook extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.ROOK;

    public Rook(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        return getAvailableHorizontalVerticalMoves(board, row, col);
    }

    @Override
    public Status move(Board board, int currentRow, int currentCol, int newRow, int newCol) {
        Space[][] a = board.getBoard();
        if(legalMove(a, newRow, newCol))
        {
            if(currentRow == newRow || currentCol == newCol)
            {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);
                return Status.SuccessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
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
