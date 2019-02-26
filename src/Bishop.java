import java.util.HashSet;

public class Bishop extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

    public Bishop(String c, int v) {
        color = c;
        value = v;
    }


    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> availablePositions = new HashSet<>();

        for (int i = -1; row + i >= 0; i--){
            for (int j = -1; col + j >= 0; j--){
                Position newPostion = new Position(row + i, col + j);
                if (legalMove(board, newPostion)){
                    availablePositions.add(newPostion);
                }
            }
        }

        return availablePositions;
    }

    public Status move(Space a[][], int currentRow, int currentCol, int newRow, int newCol)
    //Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
    {
        if (legalMove(a, newRow, newCol)) {
            if (Math.abs(newRow - currentRow) == Math.abs(newCol - currentCol)) {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);

                return Status.SucessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
            } else
                return Status.FailedMove();
        } else
            return Status.FailedMove();
    }

    public int getvalue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    @Override
    public ChessPieceType getType() {
        return chessPieceType;
    }
}
