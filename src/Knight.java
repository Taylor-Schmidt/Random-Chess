import java.util.HashSet;

public class Knight extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KNIGHT;

    public Knight(String c, int v) {
        color = c;
        value = v;
    }

    private static final Position[] directionVectors = new Position[]{new Position(2, 1),
            new Position(2, -1), new Position(-1, 2), new Position(-1, -2),
            new Position(-2, 1), new Position(-2, -1), new Position(1, 2),
            new Position(1, -2)
    };

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> availableMoves = new HashSet<>();
        for (Position direction: directionVectors){
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
