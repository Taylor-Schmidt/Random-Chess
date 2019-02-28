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
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        return getAvailableDiagonalMoves(board, row, col);
    }

    public Status move(Board board, int currentRow, int currentCol, int newRow, int newCol)
    //Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
    {
        if (getAvailableMoves(board, currentRow, currentCol).contains(new Position(newRow, newCol))) {
            board.getSpace(newRow, newCol).setPiece(this);
            board.getSpace(currentRow, currentCol).setPiece(null);
            return Status.SuccessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
        } else {
            return Status.FailedMove();
        }
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
