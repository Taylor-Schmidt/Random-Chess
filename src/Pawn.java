public class Pawn extends Piece {

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
    public void move(Space[][] a, int currentRow, int currentCol, int newRow, int newCol){
        if(legalMove(a , newRow, newCol) ) {

            if (color.equals("white")) {
                //white pieces
                if ((newRow == currentRow - 1) && (newCol == currentCol)) {
                    //Forward movement
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else if( (currentRow == 6) &&
                        ( (newRow == currentRow - 2) && (newCol == currentCol) )
                        ){
                //Two spaces for first movement
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else {
                    System.out.println("Illegal move, please try another one.");
                }
            } else {
                //black pieces
                if ((newRow == currentRow + 1) && (newCol == currentCol)) {
                    //Forward movment
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else if( (currentRow == 1) &&
                        ( (newRow == currentRow +2) && (newCol == currentCol) )
                        ){
                    //Two spaces for first movement
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else {
                    System.out.println("Illegal move, please try another one.");

                }
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
