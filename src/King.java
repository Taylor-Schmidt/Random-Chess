import java.util.HashSet;

public class King extends Piece {

    private String color;
    private int value = 0;
    private ChessPieceType chessPieceType = ChessPieceType.KING;
    private int moveCount;

    public King(String c) {
        color = c;
    }


    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> availableMoves = new HashSet<>();
        for (Position direction: Position.cardinalDirections){
            probeByDirectionVector(board, row, col, direction, availableMoves, 1, 1);
        }
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
