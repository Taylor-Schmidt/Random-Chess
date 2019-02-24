public class Bishop implements Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

    public Bishop(String c, int v) {
        color = c;
        value = v;
    }

    public void move(Space a[][], int currentX, int currentY, int newX, int newY)
    //Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
    {
        if(legalmove(a, currentX, currentY, newX, newY))
        {
            if(Math.abs(newX-currentX)==Math.abs(newY-currentY))
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

    public int getvalue() {
        return value;
    }

    public String getColor(){return color;}

    @Override
    public ChessPieceType getType() {
        return chessPieceType;
    }
}
