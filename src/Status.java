/**
 * A message that is returned to GameManager from Piece.Move.
 * Provides helpful information on the result of the move to the player.
 */
class Status {
    String message;
    String status;

    private static final String STATUS_GOOD = "good";
    static final String STATUS_BAD = "bad";

    private Status(String status, String message) {
        this.message = message;
        this.status = status;
    }

    /**
     * A default implementation of Status for easy returns when the move is successful.
     */
    static Status SuccessfulMove(Piece.ChessPieceType type, Position pBefore, Position pAfter) {
        return new Status(STATUS_GOOD, type + " moved from " + Position.parsePosition(pBefore) + " to " +
                Position.parsePosition(pAfter) + ".");
    }

    /**
     * A default implementation of Status for easy returns when the moves fails.
     */
    static Status FailedMove() {
        return new Status(STATUS_BAD, "Illegal move, please try another one.");
    }


}
