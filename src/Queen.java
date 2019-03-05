import java.util.HashSet;

public class Queen extends Piece {

    private String color;
    private int value = 9;
    private ChessPieceType chessPieceType = ChessPieceType.QUEEN;

    public Queen(String c) {
        color = c;
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> availableMoves = new HashSet<>(getAvailableDiagonalMoves(board, row, col));
        availableMoves.addAll(getAvailableHorizontalVerticalMoves(board, row, col));
        return availableMoves;
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
