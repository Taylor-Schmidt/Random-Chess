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
