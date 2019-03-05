import java.util.HashSet;

public class King extends Piece {

    private String color;
    private int value = 0;
    private ChessPieceType chessPieceType = ChessPieceType.KING;

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
