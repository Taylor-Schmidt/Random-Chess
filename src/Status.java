/**
 * A message that is returned to GameManager from Piece.Move.
 * Provides helpful information on the result of the move to the player.
 */
public class Status {
    String message;
    String status;

    public Status(String status, String message) {
        this.message = message;
        this.status = status;
    }

    public Status(String message){
        this.status = null;
        this.message = message;
    }

    /**
     * A default implementation of Status for easy returns when the move is successful.
     */
    public static Status SuccessfulMove(Piece.ChessPieceType chessPieceType, int oldRow, int oldCol, int newRow, int newCol){
        return new Status(chessPieceType + " moved from " + Position.parsePosition(oldRow, oldCol) + " to " +
                Position.parsePosition(newRow, newCol) + ".");
    }

    /**
     * A default implementation of Status for easy returns when the moves fails.
     */
    public static Status FailedMove(){
        return new Status("Illegal move, please try another one.");
    }
}
