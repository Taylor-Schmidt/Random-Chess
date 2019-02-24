public class Rook implements Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.ROOK;

    public Rook(String c, int v) {
        color = c;
        value = v;
    }
    @Override
    public void move(Space[][] a, int currentX, int currentY, int newX, int newY) {
        if(legalmove(a, currentX, currentY, newX, newY))
        {
            if(currentX == newX || currentY == newY)
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
