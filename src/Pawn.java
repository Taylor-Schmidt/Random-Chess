public class Pawn implements Piece {
    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.PAWN;

    public Pawn(String c, int v) {
        color = c; //black,white
        value = v;
    }

    @Override
    public void move(Space location[][], int currentX, int currentY, int newX, int newY)
        location[currentX][CurrentY] = null
        location[newX][newY] = new Space(this);

        //programming logic/valid moves

        //white (0 end of board y)
        if(color == "white");
            //horizontal
            if(currentX != newX)
                newX = currentX;
            if(newY != currentY++)
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
