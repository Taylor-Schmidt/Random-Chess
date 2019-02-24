public class King implements Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.KING;

    public King(String c, int v) {
        color = c;
        value = v;
    }

    @Override
    public void move(Space[][] a, int currentX, int currentY, int newX, int newY) {
        if (legalmove(a, currentX, currentY, newX, newY)){
            if((((Math.abs(currentX-newX)==1 && Math.abs(currentY-newY)==1) && Math.abs(currentY-newY)<2))||((Math.abs(currentX-newX)==1 && Math.abs(currentY-newY)==1) && Math.abs(currentX-newX)<2)){

            }

        }
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
