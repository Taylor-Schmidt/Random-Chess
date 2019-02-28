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
