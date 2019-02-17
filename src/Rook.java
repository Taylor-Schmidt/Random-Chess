public class Rook implements Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.ROOK;

    public Rook(String c, int v) {
        color = c;
        value = v;
    }
    @Override
    public void move(Space[][] a, int currentX, int currentY, int newX, int newY) {

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
