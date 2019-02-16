
public interface Piece {

    public enum ChessPieceType {
        PAWN, BISHOP, KING, QUEEN, ROOK, KNIGHT
    }

    public abstract void move(Space a[][], int currentX, int currentY, int newX, int newY);

    public int getvalue();

    public String getColor();

    public ChessPieceType getType();

}
