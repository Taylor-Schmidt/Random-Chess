import java.util.HashSet;

public class Bishop extends Piece {

    private String color;
    private int value = 3;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

    public Bishop(String c) {
        color = c;
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
