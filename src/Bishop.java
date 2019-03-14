import java.util.HashSet;

public class Bishop extends Piece {

    private String color;
    private int value = 3;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;
    private int moveCount;

    public Bishop(String c) {
        color = c;
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        return getAvailableDiagonalMoves(board, row, col);
    }

    public int getValue() {
        return value;
    }

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
