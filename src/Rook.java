import java.util.HashSet;

public class Rook extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.ROOK;
    private int moveCount;

    public Rook(String c) {
        color = c;
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        return getAvailableHorizontalVerticalMoves(board, row, col);
    }

    @Override
    public int getValue() {
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

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}
