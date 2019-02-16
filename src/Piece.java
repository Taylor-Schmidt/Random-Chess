
public interface Piece {

    enum ChessPieceType {
        PAWN, BISHOP, KING, QUEEN, ROOK, KNIGHT
    }

    void move(Space a[][], int currentX, int currentY, int newX, int newY);

    int getvalue();

    String getColor();

    ChessPieceType getType();

}
