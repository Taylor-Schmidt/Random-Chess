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
        HashSet<Position> availableMoves = new HashSet<>(getAvailableDiagonalMoves(board, row, col));
        availableMoves.addAll(getAvailableHorizontalVerticalMoves(board, row, col));
        return availableMoves;
    }

    @Override
    public Status move(Board board, int currentRow, int currentCol, int newRow, int newCol) {
        Space[][] a = board.getBoard();
        if (getAvailableMoves(board, currentRow, currentCol).contains(new Position(newRow, newCol))) {
            board.getSpace(newRow, newCol).setPiece(this);
            board.getSpace(currentRow, currentCol).setPiece(null);
            return Status.SuccessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
        } else {
            return Status.FailedMove();
        }
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
