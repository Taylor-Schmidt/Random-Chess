public class Pawn implements Piece {
    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.PAWN;

    public Pawn(String c, int v) {
        color = c; //black,white
        value = v;
    }

    @Override
    public Position[] getAvailableMoves(int row, int col) {
        return new Position[0];
    }

    @Override
    public void move(Space[][] location, int currentRow, int currentCol, int newX, int newCol){
        location[currentRow][currentCol] = null;
        location[newX][newCol] = new Space(this);

        //programming logic/valid moves

        //white (0 end of board y)
        if(color.equals("white"));
            //horizontal
            if(currentRow != newX)
                newX = currentRow;
            //if(newCol != currentCol++)

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
