import java.util.HashSet;

public class Bishop extends Piece {

    private String color;
    private int value;
    private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

    public Bishop(String c, int v) {
        color = c;
        value = v;
    }

    public static void main(String[] args) {
        Bishop bishop = new Bishop("white", 0);
        Board board = new Board();
        board.setSpace(new Space(new Pawn("black", 0)), 0, 0);
        System.out.println(bishop.getAvailableMoves(board, 3, 3));
    }


    @Override
    public HashSet<Position> getAvailableMoves(Board board, int row, int col) {
        HashSet<Position> availablePositions = new HashSet<>();

        boolean encounteredIllegalMove = false;
        for (int i = -1, j = -1; row + i >= 0 && col + j >= 0 && !encounteredIllegalMove; i--, j--) {
            encounteredIllegalMove = isEncounteredIllegalMove(board, row, col, availablePositions, i, j);
        }

        encounteredIllegalMove = false;
        for (int i = -1, j = 1; row + i >= 0 && col + j < board.getCols() && !encounteredIllegalMove; i--, j++) {
            encounteredIllegalMove = isEncounteredIllegalMove(board, row, col, availablePositions, i, j);
        }

        encounteredIllegalMove = false;
        for (int i = 1, j = -1; row + i < board.getRows() && col + j >= 0 && !encounteredIllegalMove; i++, j--) {
            encounteredIllegalMove = isEncounteredIllegalMove(board, row, col, availablePositions, i, j);
        }

        encounteredIllegalMove = false;
        for (int i = 1, j = 1; row + i < board.getRows() && col + j < board.getCols() && !encounteredIllegalMove; i++, j++) {
            encounteredIllegalMove = isEncounteredIllegalMove(board, row, col, availablePositions, i, j);
        }

        return availablePositions;
    }

    private boolean isEncounteredIllegalMove(Board board, int row, int col, HashSet<Position> availablePositions, int i, int j) {
        boolean encounteredIllegalMove;
        Position newPosition = new Position(row + i, col + j);
        encounteredIllegalMove = !legalMove(board, newPosition);

        if (!encounteredIllegalMove) {
            availablePositions.add(newPosition);
        }
        return encounteredIllegalMove;
    }

    public Status move(Board board, int currentRow, int currentCol, int newRow, int newCol)
    //Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
    {
        if (getAvailableMoves(board, currentRow, currentCol).contains(new Position(newRow, newCol))) {
            board.getSpace(newRow, newCol).setpiece(this);
            board.getSpace(currentRow, currentCol).setpiece(null);
            return Status.SucessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
        } else {
            return Status.FailedMove();
        }
//        if (legalMove(board, newRow, newCol)) {
//            if (Math.abs(newRow - currentRow) == Math.abs(newCol - currentCol)) {
//                board.getSpace(newRow, newCol).setpiece(this);
//                board.getSpace(currentRow, currentCol).setpiece(null);
//
//                return Status.SucessfulMove(chessPieceType, currentRow, currentCol, newRow, newCol);
//            } else
//                return Status.FailedMove();
//        } else
//            return Status.FailedMove();
    }

    public int getvalue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    @Override
    public ChessPieceType getType() {
        return chessPieceType;
    }
}
