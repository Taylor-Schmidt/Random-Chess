
public interface Piece {

    enum ChessPieceType {
        PAWN, BISHOP, KING, QUEEN, ROOK, KNIGHT
    }

    void move(Space a[][], int currentX, int currentY, int newX, int newY);

    int getvalue();

    String getColor();

    ChessPieceType getType();

    default boolean legalmove(Space a[][], int currentX, int currentY, int newX, int newY)
    {
        if(a[newX][newY]==null)
            return false;
        else if((newX<0)||(newY<0)||(newX>a.length)||(newY>a.length))
            return false;
        else if(!(a[newX][newY].getpiece()==null))
            return false;
        //Still need to check if space you are moving a piece to has a piece of the same color
        else
            return true;
    }

}
