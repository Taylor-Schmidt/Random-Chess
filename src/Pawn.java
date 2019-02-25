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
    public void move(Space[][] a, int currentRow, int currentCol, int newRow, int newCol) {
        if ((legalMove(a, newRow, newCol)) && (chessPieceType == ChessPieceType.PAWN)) {

            if (color.equals("white")) {
                //white pieces
                if ((newCol == currentCol) && (hasAPiece(a, newRow, newCol))) {
                    //disable capture forward
                    //Checks if column is same, and there is a piece in the new spot
                    System.out.println("Illegal move, please try another one.");
                }
                else if ((newRow == currentRow - 1) && (newCol == currentCol)) {
                    //Forward movement
                    //Checks if column is same, and if moving correct distance
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else if ((currentRow == 6) &&
                        ((newRow == currentRow - 2) && (newCol == currentCol))
                ) {
                    //Two spaces for first movement
                    //First checks if piece is in original row
                    //Then allows movement two spaces instead of one
                    //Make sure still in same column
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else if ((newRow == currentRow - 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, newRow, newCol) && !colorsAreTheSame(a, newRow, newCol))
                ) {
                    //Diagonal capture
                    //Checks if the row was still changed
                    //Then checks if column is next to current one
                    //Ends with checking if there is a piece of different color, else fails
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else {
                    //Invalid move
                    System.out.println("Illegal move, please try another one.");
                }
            } else {
                //black pieces
                if ((newCol == currentCol) && (hasAPiece(a, newRow, newCol))) {
                    //disable capture forward
                    //Checks if column is same, and there is a piece in the new spot
                    System.out.println("Illegal move, please try another one.");
                }
                else if ((newRow == currentRow + 1) && (newCol == currentCol)) {
                    //Forward movement
                    //Checks if column is same, and if moving correct distance
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else if ((currentRow == 1) &&
                        ((newRow == currentRow + 2) && (newCol == currentCol))
                ) {
                    //Two spaces for first movement
                    //First checks if piece is in original row
                    //Then allows movement two spaces instead of one
                    //Make sure still in same column
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else if ((newRow == currentRow + 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, newRow, newCol) && !colorsAreTheSame(a, newRow, newCol))
                ) {
                    //Diagonal capture
                    //Checks if the row was still changed
                    //Then checks if column is next to current one
                    //Ends with checking if there is a piece of different color, else fails
                    a[newRow][newCol].setpiece(this);
                    a[currentRow][currentCol].setpiece(null);
                }
                else {
                    //Invalid move
                    System.out.println("Illegal move, please try another one.");
                }
            }
            if ((newRow == 0) || (newRow == 7)) {
                //Promotion logic
                chessPieceType = ChessPieceType.QUEEN;
            }
        }
        if ((legalMove(a, newRow, newCol)) && (chessPieceType == ChessPieceType.QUEEN)) {
            if (currentRow == newRow || currentCol == newCol || Math.abs(newRow - currentRow) == Math.abs(newCol - currentCol)) {
                a[newRow][newCol].setpiece(this);
                a[currentRow][currentCol].setpiece(null);
            } else
                System.out.println("Illegal move, please try another one.");
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
