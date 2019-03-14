import java.util.HashSet;

public class Queen extends Piece {

    private String color;
    private int value = 9;
    private ChessPieceType chessPieceType = ChessPieceType.QUEEN;
    private int moveCount;

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
