public class King extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KING;

    public King(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public Position[] getAvailableMoves(int row, int col) {
        return new Position[0];
    }

    @Override
    public Status move(Space[][] a, int currentRow, int currentCol, int newRow, int newCol) {
        if (legalMove(a, newRow, newCol)){
            if((((Math.abs(currentRow-newRow)==1 && Math.abs(currentCol-newCol)==1) && Math.abs(currentCol-newCol)<2))||((Math.abs(currentRow-newRow)==1 && Math.abs(currentCol-newCol)==1) && Math.abs(currentRow-newRow)<2)){

            }

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
