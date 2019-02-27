import java.util.HashSet;

public class Knight extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KNIGHT;

    public Knight(String c, int v) {
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
            if((((newRow==currentRow+2)||(newRow==currentRow-2))&&((newCol==currentCol+1)||(newCol==currentCol-1)))||((newCol==currentCol+2||(newCol==currentCol-2))&&((newRow==currentRow+1)||(newRow==currentRow-1))))
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
