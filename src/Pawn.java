import java.util.HashSet;

public class Pawn extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.PAWN;


    public Pawn(String c, int v) {
        color = c; //black,white
        value = v;
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> AvailablePositions= new HashSet<>();;
        if(color=="black")
        {
            Position p = new Position(row+1, col+1);
            AvailablePositions.add(p);
            p= new Position(row+1, col-1);
            AvailablePositions.add(p);
        }
        else
        {
            Position p = new Position(row-1, col+1);
            AvailablePositions.add(p);
            p= new Position(row-1, col-1);
            AvailablePositions.add(p);
        }
        return AvailablePositions;
    }

    @Override
    public Status move(Board b, int currentRow, int currentCol, int newRow, int newCol) {
        Space[][] a = b.getBoard();
        if ((legalMove(a, newRow, newCol)) && (chessPieceType == ChessPieceType.PAWN)) {
            if (color.equals("white")) {
                //white pieces
                if ((newCol == currentCol) && (hasAPiece(a, newRow, newCol))) {
                    //disable capture forward
                    //Checks if column is same, and there is a piece in the new spot
                    return Status.FailedMove();
                }
                else if ((newRow == currentRow - 1) && (newCol == currentCol)) {
                    //Forward movement
                    //Checks if column is same, and if moving correct distance
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);
                }
                else if ((currentRow == 6) &&
                        ((newRow == currentRow - 2) && (newCol == currentCol)) &&
                        ((!hasAPiece(a,4,newCol)) && (!hasAPiece(a,5,newCol)))
                ) {
                    //Two spaces for first movement
                    //First checks if piece is in original row
                    //Then allows movement two spaces instead of one
                    //Also checks if there is a piece in the space needed to move
                    //Make sure still in same column
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                }
                else if ((newRow == currentRow - 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, newRow, newCol) && !colorsAreTheSame(a, newRow, newCol))
                ) {
                    //Diagonal capture
                    //Checks if the row was still changed
                    //Then checks if column is next to current one
                    //Ends with checking if there is a piece of different color, else fails
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                }
                else if ((newRow == currentRow - 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, currentRow, newCol) && !colorsAreTheSame(a, currentRow, newCol)) &&
                        (currentRow == 3)
                ) {
                    //En passant
                    //checks row is incremented
                    //Then checks if column is changed
                    //then checks if there is a piece next to it
                    //Lastly checks if the row is the appropriate spot for performing this action
                    a[newRow+1][newCol].setPiece(null);
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);
                }
                else {
                    //Invalid move
                    //default if others fail
                    return Status.FailedMove();
                }
                //promotion logic
                if (newRow == 0) {
                    //Promotion logic
                    //Checks if pawn is in last row on board
                    //Then sets a new piece in it's place
                    a[newRow][newCol].setPiece(new Queen(this.color,0));
                }
                return Status.SuccessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
            }
            else {
                //black pieces
                if ((newCol == currentCol) && (hasAPiece(a, newRow, newCol))) {
                    //disable capture forward
                    //Checks if column is same, and there is a piece in the new spot
                    return Status.FailedMove();
                }
                else if ((newRow == currentRow + 1) && (newCol == currentCol)) {
                    //Forward movement
                    //Checks if column is same, and if moving correct distance
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                }
                else if ((currentRow == 1) &&
                        ((newRow == currentRow + 2) && (newCol == currentCol)) &&
                ((!hasAPiece(a,2,newCol)) && (!hasAPiece(a,3,newCol)))
                ) {
                    //Two spaces for first movement
                    //First checks if piece is in original row
                    //Then allows movement two spaces instead of one
                    //Also checks if there is a piece in the space needed to move
                    //Make sure still in same column
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                }
                else if ((newRow == currentRow + 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, newRow, newCol) && !colorsAreTheSame(a, newRow, newCol))
                ) {
                    //Diagonal capture
                    //Checks if the row was still changed
                    //Then checks if column is next to current one
                    //Ends with checking if there is a piece of different color, else fails
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                }
                else if ((newRow == currentRow + 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, currentRow, newCol) && !colorsAreTheSame(a, currentRow, newCol)) &&
                        (currentRow == 4)
                ) {
                    //En passant
                    //checks row is incremented
                    //Then checks if column is changed
                    //then checks if there is a piece next to it
                    //Lastly checks if the row is the appropriate spot for performing this action
                    a[newRow-1][newCol].setPiece(null);
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                }
                else {
                    //Invalid move
                    //default if others fail
                    return Status.FailedMove();
                }
                //promotion logic
                if (newRow == 7) {
                    //Promotion logic
                    //Checks if pawn is in last row on board
                    //Then sets a new piece in it's place
                    a[newRow][newCol].setPiece(new Queen(this.color,0));
                }
                return Status.SuccessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
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
