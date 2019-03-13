import java.util.HashSet;

public class Pawn extends Piece {

    private String color;
    private int value = 1;
    private ChessPieceType chessPieceType = ChessPieceType.PAWN;


    public Pawn(String c) {
        color = c; //black,white
    }

    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> availablePositions = new HashSet<>();

        int newRow;
        int firstMoveRowOption;
        boolean isFirstMove;

        if (color.equals("black")) {
            newRow = row + 1;
            firstMoveRowOption = row + 2;
            isFirstMove = row == 1;
        } else {
            newRow = row - 1;
            firstMoveRowOption = row - 2;
            isFirstMove = row == 6;
        }

        if (isFirstMove){
            Position newPosition = new Position(firstMoveRowOption, col);
//            System.out.println(newPosition);
            if (legalMove(board, newPosition)){
                availablePositions.add(newPosition);
            }
        }

        Position center = new Position(newRow, col);
        Position left = new Position(newRow, col - 1);
        Position right = new Position(newRow, col + 1);

        if (legalMove(board, center)){
            availablePositions.add(center);
        }
        if (board.positionIsWithinBounds(left) && isASpace(board, left)){
            if (hasAPiece(board, left)){
                if (colorsAreDifferent(board, left)){
                    availablePositions.add(left);
                }
            } else {
                if (hasAPiece(board, new Position(row, col - 1)) && colorsAreDifferent(board, new Position(row, col - 1))){
                    availablePositions.add(left);
                }
            }
        }
        if (board.positionIsWithinBounds(right) && isASpace(board, right)){
            if (hasAPiece(board, right)){
                if (colorsAreDifferent(board, right)){
                    availablePositions.add(right);
                }
            } else {
                if (hasAPiece(board, new Position(row, col + 1)) && colorsAreDifferent(board, new Position(row, col + 1))){
                    availablePositions.add(right);
                }
            }
        }


//        if (color.equals("black")) {
//            Position p = new Position(row + 1, col + 1);
//            availablePositions.add(p);
//            p = new Position(row + 1, col - 1);
//            availablePositions.add(p);
//        } else {
//            Position p = new Position(row - 1, col + 1);
//            availablePositions.add(p);
//            p = new Position(row - 1, col - 1);
//            availablePositions.add(p);
//        }
            return availablePositions;
    }

    @Override
    public Status move(Board b, int currentRow, int currentCol, int newRow, int newCol) {
        Space[][] a = b.getBoard();
        Position newPosition = new Position(newRow, newRow);

        if ((legalMove(b, newPosition)) && (chessPieceType == ChessPieceType.PAWN)) {
            if (color.equals("white")) {
                //white pieces
                if ((newCol == currentCol) && (hasAPiece(a, newRow, newCol))) {
                    //disable capture forward
                    //Checks if column is same, and there is a piece in the new spot
                    return Status.FailedMove();
                } else if ((newRow == currentRow - 1) && (newCol == currentCol)) {
                    //Forward movement
                    //Checks if column is same, and if moving correct distance
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);
                } else if ((currentRow == 6) &&
                        ((newRow == currentRow - 2) && (newCol == currentCol)) &&
                        ((!hasAPiece(a, 4, newCol)) && (!hasAPiece(a, 5, newCol)))
                ) {
                    //Two spaces for first movement
                    //First checks if piece is in original row
                    //Then allows movement two spaces instead of one
                    //Also checks if there is a piece in the space needed to move
                    //Make sure still in same column
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                } else if ((newRow == currentRow - 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, newRow, newCol) && colorsAreDifferent(a, newRow, newCol))
                ) {
                    //Diagonal capture
                    //Checks if the row was still changed
                    //Then checks if column is next to current one
                    //Ends with checking if there is a piece of different color, else fails
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                } else if ((newRow == currentRow - 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, currentRow, newCol) && colorsAreDifferent(a, currentRow, newCol)) &&
                        (currentRow == 3)
                ) {
                    //En passant
                    //checks row is incremented
                    //Then checks if column is changed
                    //then checks if there is a piece next to it
                    //Lastly checks if the row is the appropriate spot for performing this action
                    a[newRow + 1][newCol].setPiece(null);
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);
                } else {
                    //Invalid move
                    //default if others fail
                    return Status.FailedMove();
                }
                //promotion logic
                if (newRow == 0) {
                    //Promotion logic
                    //Checks if pawn is in last row on board
                    //Then sets a new piece in it's place
                    a[newRow][newCol].setPiece(new Queen(this.color));
                }
                return Status.SuccessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
            } else {
                //black pieces
                if ((newCol == currentCol) && (hasAPiece(a, newRow, newCol))) {
                    //disable capture forward
                    //Checks if column is same, and there is a piece in the new spot
                    return Status.FailedMove();
                } else if ((newRow == currentRow + 1) && (newCol == currentCol)) {
                    //Forward movement
                    //Checks if column is same, and if moving correct distance
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                } else if ((currentRow == 1) &&
                        ((newRow == currentRow + 2) && (newCol == currentCol)) &&
                        ((!hasAPiece(a, 2, newCol)) && (!hasAPiece(a, 3, newCol)))
                ) {
                    //Two spaces for first movement
                    //First checks if piece is in original row
                    //Then allows movement two spaces instead of one
                    //Also checks if there is a piece in the space needed to move
                    //Make sure still in same column
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                } else if ((newRow == currentRow + 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, newRow, newCol) && colorsAreDifferent(a, newRow, newCol))
                ) {
                    //Diagonal capture
                    //Checks if the row was still changed
                    //Then checks if column is next to current one
                    //Ends with checking if there is a piece of different color, else fails
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                } else if ((newRow == currentRow + 1) &&
                        ((newCol == currentCol + 1) || (newCol == currentCol - 1)) &&
                        (hasAPiece(a, currentRow, newCol) && colorsAreDifferent(a, currentRow, newCol)) &&
                        (currentRow == 4)
                ) {
                    //En passant
                    //checks row is incremented
                    //Then checks if column is changed
                    //then checks if there is a piece next to it
                    //Lastly checks if the row is the appropriate spot for performing this action
                    a[newRow - 1][newCol].setPiece(null);
                    a[newRow][newCol].setPiece(this);
                    a[currentRow][currentCol].setPiece(null);

                } else {
                    //Invalid move
                    //default if others fail
                    return Status.FailedMove();
                }
                //promotion logic
                if (newRow == 7) {
                    //Promotion logic
                    //Checks if pawn is in last row on board
                    //Then sets a new piece in it's place
                    a[newRow][newCol].setPiece(new Queen(this.color));
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
