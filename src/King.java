import java.util.HashSet;

public class King extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KING;

    public King(String c, int v) {
        color = c;
        value = v;
    }


    @Override
    public HashSet<Position> getAvailableMoves(int row, int col) {
        return new HashSet<>();
    }

    @Override
    public Status move(Space[][] a, int currentRow, int currentCol, int newRow, int newCol) {
        if (legalMove(a, newRow, newCol)){
            if((Math.abs(currentCol-newCol)+Math.abs(currentRow-newRow))<=2){
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);
                return Status.SucessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
            }
            else
                return Status.FailedMove();

        }
        return Status.FailedMove();
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
