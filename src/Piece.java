
public interface Piece {

    enum ChessPieceType {
        PAWN, BISHOP, KING, QUEEN, ROOK, KNIGHT
    }

    Position[] getAvailableMoves(int row, int col);

    void move(Space a[][], int currentRow, int currentCol, int newRow, int newCol);

    int getvalue();

    String getColor();

    ChessPieceType getType();

    default boolean legalmove(Space a[][], int currentRow, int currentCol, int newRow, int newCol)
    {
        if(a[newRow][newCol]==null)
            return false;
        else if((newRow<0)||(newCol<0)||(newRow>a.length)||(newCol>a[0].length))
            return false;
        else if(!(a[newRow][newCol].getpiece()==null))
            return false;
        else if(!(a[newCol][newRow].getpiece()==null)) {
            return a[newRow][newCol].getpiece().getColor() != getColor();
        }
                //Still need to check if space you are moving a piece to has a piece of the same color
            else
                return true;
        }
    }

}
