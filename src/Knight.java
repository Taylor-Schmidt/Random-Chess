public class Knight implements Piece {private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KNIGHT;

    public Knight(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public void move(Space[][] a, int currentX, int currentY, int newX, int newY) {
        if(legalmove(a, currentX, currentY, newX, newY))
        {
            if((((newX==currentX+2)||(newX==currentX-2))&&((newY==currentY+1)||(newY==currentY-1)))||((newY==currentY+2||(newY==currentY-2))&&((newX==currentX+1)||(newX==currentY-1))))
            {
                a[newX][newY].setpiece(this);
                a[currentX][currentY].setpiece(null);
            }
            else
                System.out.println("Illegal move, please try another one.");
        }
        else
            System.out.println("Illegal move, please try another one.");
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
